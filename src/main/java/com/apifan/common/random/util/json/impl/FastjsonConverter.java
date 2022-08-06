package com.apifan.common.random.util.json.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.apifan.common.random.util.json.JsonConverter;

import java.util.List;
import java.util.Map;

/**
 * Fastjson转换器接口实现类
 *
 * <p>
 * 注意: 需要手动引入fastjson依赖
 * </p>
 *
 * @author yin
 * @since 1.0.16
 */
public class FastjsonConverter implements JsonConverter {

    /**
     * 解析JSON字符串并转换为单个对象
     *
     * @param text        待解析的JSON字符串
     * @param targetClass 目标类
     * @return 对象
     */
    @Override
    public <T> T parseObject(String text, Class<T> targetClass) {
        return JSON.parseObject(text, targetClass);
    }

    /**
     * 解析JSON字符串并转换为Map列表
     *
     * @param text 待解析的JSON字符串
     * @return Map列表
     */
    @Override
    public List<Map<String, Object>> parseMapList(String text) {
        return JSON.parseObject(text, new TypeReference<List<Map<String, Object>>>() {
        });
    }

    /**
     * 解析JSON字符串并转换为对象列表
     *
     * @param text        待解析的JSON字符串
     * @param targetClass 目标类
     * @param <T>         泛型
     * @return 对象列表
     */
    @Override
    public <T> List<T> parseObjectList(String text, Class<T> targetClass) {
        return JSON.parseArray(text, targetClass);
    }

    /**
     * 对象转换为JSON字符串
     *
     * @param obj 对象
     * @return JSON字符串
     */
    @Override
    public String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }
}
