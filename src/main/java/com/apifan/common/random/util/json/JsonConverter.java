package com.apifan.common.random.util.json;

import java.util.List;
import java.util.Map;

/**
 * 自定义Json转换器接口
 * <p>
 * 通过接口抽象方式来快速支持多种类型的json库
 * </p>
 *
 * @author yin
 * @since 1.0.16
 */
public interface JsonConverter {

    /**
     * 解析JSON字符串并转换为单个对象
     *
     * @param text        待解析的JSON字符串
     * @param targetClass 目标类
     * @param <T>         泛型
     * @return 对象
     */
    <T> T parseObject(String text, Class<T> targetClass);

    /**
     * 解析JSON字符串并转换为Map列表
     *
     * @param text 待解析的JSON字符串
     * @return Map列表
     */
    List<Map<String, Object>> parseMapList(String text);

    /**
     * 解析JSON字符串并转换为对象列表
     *
     * @param text        待解析的JSON字符串
     * @param targetClass 目标类
     * @param <T>         泛型
     * @return 对象列表
     */
    <T> List<T> parseObjectList(String text, Class<T> targetClass);

    /**
     * 对象转换为JSON字符串
     *
     * @param obj 对象
     * @return JSON字符串
     */
    String toJson(Object obj);
}
