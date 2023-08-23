package cn.miaow.listen.utils;

import cn.miaow.listen.model.hj212.CpData;
import cn.miaow.listen.model.hj212.Data;
import cn.miaow.listen.model.hj212.DataFlag;
import cn.miaow.listen.model.hj212.Pack;
import com.alibaba.fastjson2.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * hj212协议相关工具类
 *
 * @author miaow
 */
@Slf4j
public class Hj212Util {

    //hj212 数据应答
    public static final String DATA_RESPONSE = "9014";

    /**
     * 转换组装数据
     *
     * @param segment 原报文
     */
    public static Data convert(char[] segment) {
        if (segment == null || segment.length == 0) {
            log.error("数据区异常为空");
            return null;
        }
        Map<String, String> dataMap = getStringStringMap(segment);
        // 序列化转换
        CpData cpData = JSONObject.parseObject(JSONObject.toJSONString(dataMap), CpData.class);
        Data data = JSONObject.parseObject(JSONObject.toJSONString(dataMap), Data.class);
        if (data == null) {
            return null;
        }
        data.setCpData(cpData);

        // 判断拆分包及应答
        data.setDataFlag(convertDataFlag(data.getDataFlagStr()));
        return data;
    }

    private static Map<String, String> getStringStringMap(char[] segment) {
        String dataStr = new String(segment);
        // 去除CP= 和 && 符号, 按照;切割
        dataStr = dataStr.replaceAll("&&", "").replaceAll("CP=", "");
        Map<String, String> dataMap = new HashMap<>();
        for (String item : dataStr.split(";")) {
            if (item.contains("=")) {
                if (item.contains(",")) {
                    for (String sonItem : item.split(",")) {
                        String[] dataAry = sonItem.split("=");
                        dataMap.put(dataAry[0], dataAry[1]);
                    }
                } else {
                    String[] dataAry = item.split("=");
                    dataMap.put(dataAry[0], dataAry[1]);
                }
            }
        }
        return dataMap;
    }


    public static void send(ChannelHandlerContext ctx, Data data) {
        Pack pack = new Pack();
        pack.setHeader(new char[]{'#', '#'});
        String sb = "QN=" + data.getQn() + ";" +
                "ST=" + data.getSt() + ";" +
                "CN=" + DATA_RESPONSE + ";" +
                "PW=" + data.getPw() + ";" +
                "MN=" + data.getDeviceNo() + ";" +
                "Flag=4;";
        char[] segment = sb.toCharArray();
        int segmentLen = segment.length;
        char[] len = String.format("%04d", segmentLen).toCharArray();
        pack.setLength(len);
        pack.setSegment(segment);
        int crc = CrcVerify.crc16Checkout(segment);
        String crcStr = StringUtils.padl(Integer.toHexString(crc), 4, '0');
        pack.setCrc(crcStr.toCharArray());
        pack.setFooter(new char[]{'\r', '\n'});
        ctx.channel().writeAndFlush(pack);
    }

    /**
     * 转换 数据段标记
     */
    private static List<DataFlag> convertDataFlag(String flag) {
        if (flag != null) {
            int i = Integer.parseInt(flag.trim());

            return Stream.of(DataFlag.values())
                    .filter(sf -> sf.isMarked(i))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
