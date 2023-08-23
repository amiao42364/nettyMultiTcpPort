package cn.miaow.listen.utils;

/**
 * crc校验
 *
 * @author miaow
 */
public class CrcVerify {

    /**
     * CRC校验
     *
     * @param msg 消息
     * @return DATA_CRC 校验码
     */
    public static int crc16Checkout(char[] msg) {
        int crcReg, check;
        crcReg = 0xFFFF;
        for (char c : msg) {
            crcReg = (crcReg >> 8) ^ c;
            for (int j = 0; j < 8; j++) {
                check = crcReg & 0x0001;
                crcReg >>= 1;
                if (check == 0x0001) {
                    crcReg ^= 0xA001;
                }
            }
        }
        return crcReg;
    }

    public static boolean verify(char[] msg, char[] crc) {
        int crc16 = crc16Checkout(msg);
        int crcSrc = Convert.parseInt(new String(crc), 16, 0);
        return crc16 == crcSrc;
    }
}
