package cn.miaow.listen.handler;

import cn.miaow.listen.model.Result20013;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 端口20013消息接收
 *
 * @author miaow
 */
@Slf4j
@ChannelHandler.Sharable
public class ServerHandler20013 extends SimpleChannelInboundHandler<Result20013> {

    /**
     * 当从客户端接收到一个消息时被调用
     * msg 解析后的数据信息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Result20013 message) {

    }

    /**
     * 在与客户端的连接已经建立之后将被调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("新增网关设备连接成功，远端地址为：{}", ctx.channel().remoteAddress());
    }

    /**
     * 客户端与服务端断开连接时调用
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("网关: {} 服务端连接关闭...", ctx.channel().remoteAddress());
    }

    /**
     * 服务端接收客户端发送过来的数据结束之后调用
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    /**
     * 在处理过程中引发异常时被调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("remoteAddress: {},连接异常：{}", ctx.channel().remoteAddress(), cause);
    }
}
