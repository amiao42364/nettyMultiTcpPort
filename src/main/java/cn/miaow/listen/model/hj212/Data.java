package cn.miaow.listen.model.hj212;

import com.alibaba.fastjson2.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * hj212 数据段
 *
 * @author miaow
 */
@lombok.Data
public class Data implements Serializable {
    private static final long serialVersionUID = 5939797776831700574L;

    /**
     * 请求编码 QN, 精确到毫秒的时间戳: QN=YYYYMMDDhhmmsszzz, 用来唯一标识一次命令交互
     */
    @JSONField(name = "QN")
    private String qn;

    /**
     * 系统编码 ST, ST=22代表空气质量监测
     */
    @JSONField(name = "ST")
    private String st;


    /**
     * 命令编码 CN, CN=2011代表取污染物实时数据, 用于启动现场机上传实时数据
     */
    @JSONField(name = "CN")
    private String cn;


    /**
     * 访问密码, PW=访问密码
     */
    @JSONField(name = "PW")
    private String pw;

    /**
     * 设备唯一标识 MN, MN=设备唯一标识,这个标识固化在设备中,用于唯一标识一个设备
     */
    @JSONField(name = "MN")
    private String deviceNo;

    /**
     * 是否拆分包及应答标志
     */
    private List<DataFlag> dataFlag;

    /**
     * 是否拆分包及应答标志
     */
    @JSONField(name = "Flag")
    private String dataFlagStr;

    /**
     * 总包数, 指示本次通讯中总共包含的包数
     */
    @JSONField(name = "PNUM")
    private Integer pNum;

    /**
     * 包号, 指示当前数据包的包号
     */
    @JSONField(name = "PNO")
    private Integer pNo;

    /**
     * hj212 数据区
     */
    @JSONField(name = "CP")
    private CpData cpData;

    /**
     * 数据接收时间戳
     */
    private Long receiveTime;
}
