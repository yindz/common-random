package com.apifan.common.random.util.json.impl;

import com.apifan.common.random.util.json.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Jackson转换器接口实现类
 *
 * <p>
 * 注意: 需要手动引入jackson依赖
 * </p>
 *
 * @author yin
 * @since 1.0.16
 */
public class JacksonConverter implements JsonConverter {
    private static final Logger log = LoggerFactory.getLogger(JacksonConverter.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    /**
     * 解析JSON字符串并转换为单个对象
     *
     * @param text        待解析的JSON字符串
     * @param targetClass 目标类
     * @return 对象
     */
    @Override
    public <T> T parseObject(String text, Class<T> targetClass) {
        try {
            return mapper.readValue(text, targetClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析JSON字符串并转换为Map列表
     *
     * @param text 待解析的JSON字符串
     * @return Map列表
     */
    @Override
    public List<Map<String, Object>> parseMapList(String text) {
        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, Map.class);
        try {
            return mapper.readValue(text, collectionType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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
        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, targetClass);
            return mapper.readValue(text, collectionType);
        } catch (IOException e) {
            log.error("解析json出现异常", e);
        }
        return null;
    }

    /**
     * 对象转换为JSON字符串
     *
     * @param obj 对象
     * @return JSON字符串
     */
    @Override
    public String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
