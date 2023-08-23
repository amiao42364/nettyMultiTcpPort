package cn.miaow.listen.encoder;

import cn.miaow.listen.model.hj212.Pack;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 端口20014数据发送
 * hj212-2017协议数据
 *
 * @author miaow
 */
@Slf4j
public class MsgEncoder20014 extends MessageToByteEncoder<Pack> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Pack pack, ByteBuf byteBuf) {
        log.debug("需要回复数据, 开始回复...");
        String msg = new String(pack.getHeader())
                + new String(pack.getLength())
                + new String(pack.getSegment())
                + new String(pack.getCrc())
                + new String(pack.getFooter());
        log.debug("回复数据为{}", msg);
        //写入字节缓冲区
        byteBuf.writeBytes(msg.getBytes());
    }
}
