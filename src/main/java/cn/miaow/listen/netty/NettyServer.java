package cn.miaow.listen.netty;

import com.alibaba.fastjson2.JSONObject;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * netty服务端
 *
 * @author miaow
 */
@Component
@Slf4j
public class NettyServer {
    /**
     * 端口参数，配置与配置文件中具体参数类型如：
     * netty:
     * port: 10013, 10014, 20013, 20014
     * Netty服务启动后获取到配置参数，监听配置相对应的端口
     */
    private final PortDefinition portDefinition;

    private ChannelFuture future = null;

    private NioEventLoopGroup boss = null;

    private NioEventLoopGroup worker = null;

    private final ServerBootstrap bootstrap = new ServerBootstrap();

    public NettyServer(PortDefinition portDefinition) {
        this.portDefinition = portDefinition;
    }

    public void start() {
        log.info("正在启动websocket服务...");
        boss = new NioEventLoopGroup();
        worker = new NioEventLoopGroup();

        //绑定group
        bootstrap.group(boss, worker)
                //设置并绑定Reactor线程池：EventLoopGroup，EventLoop就是处理所有注册到本线程的Selector上面的Channel
                .channel(NioServerSocketChannel.class)
                //服务端可连接队列数,对应TCP/IP协议listen函数中backlog参数
                .option(ChannelOption.SO_BACKLOG, 1024)
                //设置TCP长连接,一般如果两个小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //handler作用于boss -- childHandler作用于worker
                .childHandler(new SocketChannelInitHandler());

        int[] ports = portDefinition.getPort();
        log.info("netty服务器在{}端口启动监听", JSONObject.toJSONString(ports));
        try {
            for (int port : ports) {
                // 绑定端口
                ChannelFuture tempFuture = bootstrap.bind(new InetSocketAddress(port)).sync();
                tempFuture.addListener(future -> {
                    if (future.isSuccess()) {
                        log.info("netty 启动成功，端口：{}", port);
                    } else {
                        log.info("netty 启动失败，端口：{}", port);
                    }
                });
                tempFuture.channel().closeFuture().addListener((ChannelFutureListener) channelFuture -> tempFuture.channel().close());
            }
        } catch (Exception e) {
            log.error("netty 启动时发生异常-------{}", e.getMessage());
        }
    }

    @PreDestroy
    public void stop() {
        if (future != null) {
            future.channel().close().addListener(ChannelFutureListener.CLOSE);
            future.awaitUninterruptibly();
            boss.shutdownGracefully();
            worker.shutdownGracefully();
            future = null;
            log.info("netty服务关闭");
        }
    }
}
