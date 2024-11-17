package com.github.yindz.random.util;

import com.github.yindz.random.util.json.JsonConverter;
import com.github.yindz.random.util.json.impl.FastjsonConverter;
import com.github.yindz.random.util.json.impl.GsonConverter;
import com.github.yindz.random.util.json.impl.JacksonConverter;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * JSON工具类
 *
 * <p>
 * 从 1.0.16 起，不再强依赖 jackson; <br>
 * 支持目前常用的以下3种json第三方库: jackson/fastjson2/gson <br>
 * 检测顺序：jackson>fastjson2>gson <br>
 * 注意: 不要忘记手动添加依赖
 * </p>
 *
 * @author yin
 * @since 1.0.16
 */
public class JsonUtils {
    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    private static final JsonConverter jsonConverter;

    static {
        jsonConverter = getRealJsonConverter();
    }

    /**
     * 对象转换为JSON字符串
     *
     * @param obj 对象
     * @return JSON字符串
     */
    public static String toJson(Object obj) {
        Preconditions.checkNotNull(obj, "对象为空");
        return jsonConverter.toJson(obj);
    }

    /**
     * 解析JSON字符串并转换为单个对象
     *
     * @param text        待解析的JSON字符串
     * @param targetClass 目标类
     * @param <T>         泛型
     * @return 对象
     */
    public static <T> T parseObject(String text, Class<T> targetClass) {
        Preconditions.checkArgument(StringUtils.isNotBlank(text), "待解析的JSON字符串为空");
        Preconditions.checkNotNull(targetClass, "目标类为空");
        return jsonConverter.parseObject(text, targetClass);
    }

    /**
     * 解析JSON字符串并转换为对象列表
     *
     * @param text        待解析的JSON字符串
     * @param targetClass 目标类
     * @param <T>         泛型
     * @return 对象列表
     */
    public static <T> List<T> parseObjectList(String text, Class<T> targetClass) {
        Preconditions.checkArgument(StringUtils.isNotBlank(text), "待解析的JSON字符串为空");
        Preconditions.checkNotNull(targetClass, "目标类为空");
        return jsonConverter.parseObjectList(text, targetClass);
    }

    /**
     * 解析JSON字符串并转换为Map列表
     *
     * @param text 待解析的JSON字符串
     * @return Map列表
     */
    public static List<Map<String, Object>> parseMapList(String text) {
        Preconditions.checkArgument(StringUtils.isNotBlank(text), "待解析的JSON字符串为空");
        return jsonConverter.parseMapList(text);
    }

    /**
     * 获取JSON转换器实例
     *
     * @return JSON转换器实例
     */
    private static JsonConverter getRealJsonConverter() {
        if (ResourceUtils.isClassLoaded("com.fasterxml.jackson.databind.ObjectMapper")) {
            log.info("将使用 jackson");
            return new JacksonConverter();
        } else if (ResourceUtils.isClassLoaded("com.alibaba.fastjson2.JSON")) {
            log.info("将使用 fastjson2");
            return new FastjsonConverter();
        } else if (ResourceUtils.isClassLoaded("com.google.gson.Gson")) {
            log.info("将使用 gson");
            return new GsonConverter();
        } else {
            throw new RuntimeException("没有找到可用的JSON库");
        }
    }
}
