package cn.miaow.listen.encoder;

import cn.miaow.listen.model.Result20013;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 端口20013数据发送
 *
 * @author miaow
 */
@Slf4j
public class MsgEncoder20013 extends MessageToByteEncoder<Result20013> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Result20013 o, ByteBuf byteBuf) {
        log.debug("需要回复数据, 开始回复...");
        String msg = "success";
        log.debug("回复数据为{}", msg);
        //写入字节缓冲区
        byteBuf.writeBytes(msg.getBytes());
    }
}
