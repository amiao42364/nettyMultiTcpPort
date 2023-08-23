package cn.miaow.listen.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 类型转换器
 *
 * @author miaow
 */

public class Convert {
    /**
     * 转换为int
     * 如果给定的值为空,或者转换失败,返回默认值
     * 转换失败不会报错
     *
     * @param value        被转换的值
     * @param defaultValue 转换错误时的默认值
     * @return 结果
     */
    public static Integer toInt(Object value, Integer defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Integer) {
            return (Integer) value;
        }
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr)) {
            return defaultValue;
        }
        try {
            return Double.valueOf(valueStr.trim()).intValue();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 转换为字符串
     * 如果给定的值为null,或者转换失败,返回默认值
     * 转换失败不会报错
     *
     * @param value        被转换的值
     * @param defaultValue 转换错误时的默认值
     * @return 结果
     */
    public static String toStr(Object value, String defaultValue) {
        if (null == value) {
            return defaultValue;
        }
        if (value instanceof String) {
            return (String) value;
        }
        return value.toString();
    }

    public static int parseInt(String s, int radix, int defaultValue) {
        try {
            return Integer.parseInt(s.trim(), radix);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
