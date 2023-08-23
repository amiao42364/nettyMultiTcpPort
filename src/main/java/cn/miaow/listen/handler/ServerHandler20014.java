package cn.miaow.listen.handler;

import cn.miaow.listen.model.hj212.Data;
import cn.miaow.listen.model.hj212.DataFlag;
import cn.miaow.listen.model.hj212.Pack;
import cn.miaow.listen.utils.Convert;
import cn.miaow.listen.utils.CrcVerify;
import cn.miaow.listen.utils.Hj212Util;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 端口20014消息接收
 * hj212-2017协议数据
 *
 * @author miaow
 */
@Slf4j
@ChannelHandler.Sharable
public class ServerHandler20014 extends SimpleChannelInboundHandler<Pack> {

    /**
     * 当从客户端接收到一个消息时被调用
     * msg 解析后的数据信息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Pack message) {
        // 校验包头
        if (!"##".equals(new String(message.getHeader()))) {
            log.error("hj212数据格式错误: 错误的包头-{}", new String(message.getHeader()));
            return;
        }
        // 校验长度
        int length = Convert.toInt(new String(message.getLength()), 0);
        if (length != message.getSegment().length) {
            log.error("hj212数据格式错误:长度错误: 实际长度-{}, 获取的长度-{}", message.getSegment().length, length);
            return;
        }
        // 校验CRC
        if (!CrcVerify.verify(message.getSegment(), message.getCrc())) {
            log.error("hj212数据格式错误:crc校验失败: 实际crc-{}, 获取的crc-{}",
                    CrcVerify.crc16Checkout(message.getSegment()),
                    Convert.parseInt(new String(message.getCrc()), 16, 0));
            return;
        }
        log.debug("数据校验通过, 开始解析数据...");
        // 解析组装数据
        Data data = Hj212Util.convert(message.getSegment());
        if (data == null) {
            log.error("hj212数据格式解析失败...");
            return;
        }

        log.debug("数据解析通过, 开始入库...");
        // 入库操作

        // 判断是否需要应答
        if (DataFlag.A.isMarked(data.getDataFlag())) {
            Hj212Util.send(ctx, data);
        }
        log.info("本次接收数据结束, 共耗时{}ms", System.currentTimeMillis() - message.getTimestamp());
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
