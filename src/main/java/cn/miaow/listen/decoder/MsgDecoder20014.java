package cn.miaow.listen.decoder;

import cn.miaow.listen.model.hj212.Pack;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * 端口20014数据解析
 * hj212-2017协议数据
 *
 * @author miaow
 */
@Slf4j
public class MsgDecoder20014 extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) {
        long timestamp = System.currentTimeMillis();
        //读取数据信息
        byte[] data = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(data);
        log.info("hj212原始报文---->{} ", new String(data, StandardCharsets.UTF_8));
        if (data.length == 0) {
            log.error("接收的报文为空,ip:{}", ctx.channel().remoteAddress());
        }
        char[] chars = new String(data, StandardCharsets.UTF_8).toCharArray();
        Pack pack = new Pack();
        pack.setHeader(Arrays.copyOfRange(chars, 0, 2));
        pack.setLength(Arrays.copyOfRange(chars, 2, 6));
        pack.setSegment(Arrays.copyOfRange(chars, 6, chars.length - 4));
        pack.setCrc(Arrays.copyOfRange(chars, chars.length - 4, chars.length));
        pack.setTimestamp(timestamp);
        list.add(pack);
    }
}
