package cn.miaow.listen.netty;

import cn.miaow.listen.decoder.MsgDecoder10013;
import cn.miaow.listen.decoder.MsgDecoder10014;
import cn.miaow.listen.decoder.MsgDecoder20013;
import cn.miaow.listen.decoder.MsgDecoder20014;
import cn.miaow.listen.encoder.MsgEncoder10013;
import cn.miaow.listen.encoder.MsgEncoder10014;
import cn.miaow.listen.encoder.MsgEncoder20013;
import cn.miaow.listen.encoder.MsgEncoder20014;
import cn.miaow.listen.handler.ServerHandler10013;
import cn.miaow.listen.handler.ServerHandler10014;
import cn.miaow.listen.handler.ServerHandler20013;
import cn.miaow.listen.handler.ServerHandler20014;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * netty通道初始化
 *
 * @author miaow
 */
@Slf4j
public class SocketChannelInitHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        int port = socketChannel.localAddress().getPort();
        //不同类型连接，处理链中加入不同处理协议
        switch (port) {
            case 10013:
                pipeline.addLast(
                        new MsgDecoder10013(),
                        new MsgEncoder10013(),
                        new ServerHandler10013());
                break;
            case 10014:
                pipeline.addLast(
                        new MsgDecoder10014(),
                        new MsgEncoder10014(),
                        new ServerHandler10014());
                break;
            case 20013:
                pipeline.addLast(
                        new MsgDecoder20013(),
                        new MsgEncoder20013(),
                        new ServerHandler20013());
                break;
            case 20014:
                pipeline.addLast(
                        new LineBasedFrameDecoder(2048),
                        new MsgDecoder20014(),
                        new MsgEncoder20014(),
                        new ServerHandler20014());
                break;
            default:
                log.error("当前网关类型并不存在于配置文件中，无法初始化通道");
                break;
        }
        pipeline.addLast(
                new StringEncoder(StandardCharsets.UTF_8),
                new StringDecoder(StandardCharsets.UTF_8));
    }
}
