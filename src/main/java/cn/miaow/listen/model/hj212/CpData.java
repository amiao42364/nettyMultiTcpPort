package cn.miaow.listen.model.hj212;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * hj212 数据区
 *
 * @author miaow
 */
@Data
public class CpData implements Serializable {
    private static final long serialVersionUID = -7129272067420528175L;

    /**
     * 数据时间信息
     */
    @JSONField(name = "DataTime")
    private Date dataTime;

    /**
     * 系统时间 YYYYMMDDhhmmss
     */
    @JSONField(name = "SystemTime")
    private Date systemTime;

    /**
     * 请求回应代码
     */
    @JSONField(name = "QnRtn")
    private Integer qnRtn;

    /**
     * 执行结果回应代码
     */
    @JSONField(name = "ExeRtn")
    private Integer exeRtn;

    /**
     * 实时采样数据上报间隔 单位为秒，取值 30≤n≤3600 之间
     */
    @JSONField(name = "RtdInterval")
    private Integer rtdInterval;

    /**
     * 分钟数据上报间隔 单位为分钟，取值 1、2、3、4、5、6、10、12、15、20、30 分钟
     */
    @JSONField(name = "MinInterval")
    private Integer minInterval;

    /**
     * 数采仪开机时间 YYYYMMDDhhmmss
     */
    @JSONField(name = "RestartTime")
    private Date restartTime;

    /**
     * 细微颗粒物 PM2.5 实时数据
     */
    @JSONField(name = "a34004-Rtd")
    private Integer pm25;
    /**
     * 细微颗粒物 PM2.5 指定时间内平均值
     */
    @JSONField(name = "a34004-Avg")
    private String pm25Avg;
    /**
     * 细微颗粒物 PM2.5 监测仪器数据标记
     */
    @JSONField(name = "a34004-Flag")
    private String pm25Flag;

    /**
     * 可吸入颗粒物 PM10 实时数据
     */
    @JSONField(name = "a34002-Rtd")
    private Integer pm10;
    /**
     * 可吸入颗粒物 PM10 指定时间内平均值
     */
    @JSONField(name = "a34002-Avg")
    private String pm10Avg;
    /**
     * 可吸入颗粒物 PM10 监测仪器数据标记
     */
    @JSONField(name = "a34002-Flag")
    private String pm10Flag;

    /**
     * 总悬浮颗粒物 TSP 实时数据
     */
    @JSONField(name = "a34001-Rtd")
    private Integer tsp;
    /**
     * 总悬浮颗粒物 TSP 指定时间内平均值
     */
    @JSONField(name = "a34001-Avg")
    private String tspAvg;
    /**
     * 总悬浮颗粒物 TSP 监测仪器数据标记
     */
    @JSONField(name = "a34001-Flag")
    private String tspFlag;

    /**
     * 温度 实时数据
     */
    @JSONField(name = "a01001-Rtd")
    private Integer temperature;
    /**
     * 温度 指定时间内平均值
     */
    @JSONField(name = "a01001-Avg")
    private String temperatureAvg;
    /**
     * 温度 监测仪器数据标记
     */
    @JSONField(name = "a01001-Flag")
    private String temperatureFlag;

    /**
     * 湿度 实时数据
     */
    @JSONField(name = "a01002-Rtd")
    private Integer humidity;
    /**
     * 湿度 指定时间内平均值
     */
    @JSONField(name = "a01002-Avg")
    private String humidityAvg;
    /**
     * 湿度 监测仪器数据标记
     */
    @JSONField(name = "a01002-Flag")
    private String humidityFlag;

    /**
     * 风速 实时数据
     */
    @JSONField(name = "a01007-Rtd")
    private Integer windSpeed;
    /**
     * 风速 指定时间内平均值
     */
    @JSONField(name = "a01007-Avg")
    private String windSpeedAvg;
    /**
     * 风速 监测仪器数据标记
     */
    @JSONField(name = "a01007-Flag")
    private String windSpeedFlag;

    /**
     * 风向 实时数据
     */
    @JSONField(name = "a01008-Rtd")
    private Integer windDirection;
    /**
     * 风向 指定时间内平均值
     */
    @JSONField(name = "a01008-Avg")
    private String windDirectionAvg;
    /**
     * 风向 监测仪器数据标记
     */
    @JSONField(name = "a01008-Flag")
    private String windDirectionFlag;

    /**
     * 气压 实时数据
     */
    @JSONField(name = "a01006-Rtd")
    private Integer pressure;
    /**
     * 气压 指定时间内平均值
     */
    @JSONField(name = "a01006-Avg")
    private String pressureAvg;
    /**
     * 气压 监测仪器数据标记
     */
    @JSONField(name = "a01006-Flag")
    private String pressureFlag;

    /**
     * A权声级 实时数据
     */
    @JSONField(name = "LA-Rtd")
    private Integer noise;
    /**
     * A权声级 指定时间内平均值
     */
    @JSONField(name = "LA-Avg")
    private String noiseAvg;
    /**
     * A权声级 监测仪器数据标记
     */
    @JSONField(name = "LA-Flag")
    private String noiseFlag;

    /**
     * 噪声 实时数据
     */
    @JSONField(name = "B03-Rtd")
    private Integer bo3;
    /**
     * 噪声 指定时间内平均值
     */
    @JSONField(name = "B03-Avg")
    private String bo3Avg;
    /**
     * 噪声 监测仪器数据标记
     */
    @JSONField(name = "B03-Flag")
    private String bo3Flag;

    /**
     * 纬度 实时数据
     */
    @JSONField(name = "Lat-Rtd")
    private String lat;
    /**
     * 纬度 监测仪器数据标记
     */
    @JSONField(name = "Lat-Flag")
    private String latFlag;

    /**
     * 经度 实时数据
     */
    @JSONField(name = "Lng-Rtd")
    private String lng;
    /**
     * 经度 监测仪器数据标记
     */
    @JSONField(name = "Lng-Flag")
    private String lngFlag;

    /**
     * 一氧化碳(CO) 实时数据
     */
    @JSONField(name = "a21005-Rtd")
    private Integer co;

    /**
     * 一氧化碳(CO) 监测仪器数据标记
     */
    @JSONField(name = "a21005-Flag")
    private String coFlag;

    /**
     * 二氧化碳(CO2) 实时数据
     */
    @JSONField(name = "a05001-Rtd")
    private Integer co2;

    /**
     * 二氧化碳(CO2) 监测仪器数据标记
     */
    @JSONField(name = "a05001-Flag")
    private String co2Flag;

    /**
     * 二氧化硫(SO2) 实时数据
     */
    @JSONField(name = "a21026-Rtd")
    private Integer so2;

    /**
     * 二氧化硫(SO2) 监测仪器数据标记
     */
    @JSONField(name = "a21026-Flag")
    private String so2Flag;

}
