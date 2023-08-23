package cn.miaow.listen.model.hj212;

import lombok.Data;

/**
 * hj212 通信包
 *
 * @author miaow
 */
@Data
public class Pack {
    /**
     * 包头 固定为##
     */
    private char[] header = new char[2];

    /**
     * 数据段长度 数据段的 ASCII 字符数,例如:长 255,则写为"0255"
     */
    private char[] length = new char[4];
    /**
     * 数据段 变长的数据 长度0≤n≤1024
     */
    private char[] segment = new char[1024];
    /**
     * CRC 校验
     */
    private char[] crc = new char[4];
    /**
     * 包尾 固定为<CR><LF>（回车、换行）
     */
    private char[] footer = new char[2];

    /**
     * 开始时间戳, 用于计算耗时
     */
    private Long timestamp;
}
