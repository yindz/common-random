package com.github.yindz.random.util.json.impl;

import com.github.yindz.random.util.json.JsonConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Gson转换器接口实现类
 *
 * <p>
 * 注意: 需要手动引入gson依赖
 * </p>
 *
 * @author yin
 * @since 1.0.16
 */
public class GsonConverter implements JsonConverter {

    /**
     * 解析JSON字符串并转换为单个对象
     *
     * @param text        待解析的JSON字符串
     * @param targetClass 目标类
     * @return 对象
     */
    @Override
    public <T> T parseObject(String text, Class<T> targetClass) {
        return new Gson().fromJson(text, targetClass);
    }

    /**
     * 解析JSON字符串并转换为Map列表
     *
     * @param text 待解析的JSON字符串
     * @return Map列表
     */
    @Override
    public List<Map<String, Object>> parseMapList(String text) {
        Type typeOf = new TypeToken<List<Map<String, Object>>>() {
        }.getType();
        return new Gson().fromJson(text, typeOf);
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
        TypeToken<List<T>> typeToken = (TypeToken<List<T>>) TypeToken.getParameterized(List.class, targetClass);
        return new Gson().fromJson(new JsonReader(new StringReader(text)), typeToken.getType());
    }

    /**
     * 对象转换为JSON字符串
     *
     * @param obj 对象
     * @return JSON字符串
     */
    @Override
    public String toJson(Object obj) {
        return new Gson().toJson(obj);
    }
}
