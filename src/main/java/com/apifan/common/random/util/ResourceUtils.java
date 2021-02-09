package com.apifan.common.random.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.Resources;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 资源工具
 *
 * @author yin
 */
public class ResourceUtils {
    private static final Logger logger = LoggerFactory.getLogger(ResourceUtils.class);

    /**
     * 逐行读取资源文件
     *
     * @param fileName 资源文件名
     * @return 资源文件各行字符串
     */
    public static List<String> readLines(String fileName) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(fileName), "资源文件名为空");
        try {
            return Resources.asCharSource(Resources.getResource(fileName), Charsets.UTF_8).readLines();
        } catch (IOException e) {
            throw new RuntimeException("读取资源文件失败");
        }
    }

    /**
     * 一次性读取资源文件内容
     *
     * @param fileName 资源文件名
     * @return 资源文件内容
     */
    public static String readString(String fileName) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(fileName), "资源文件名为空");
        try {
            return Resources.asCharSource(Resources.getResource(fileName), Charsets.UTF_8).read();
        } catch (IOException e) {
            throw new RuntimeException("读取资源文件失败");
        }
    }

    /**
     * 解析json字符串资源文件为map列表
     *
     * @param fileName 资源文件名
     * @return map列表
     */
    public static List<Map<String, Object>> readAsMapList(String fileName) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(fileName), "资源文件名为空");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class);
            return objectMapper.readValue(readString(fileName), collectionType);
        } catch (IOException e) {
            logger.error("解析json出现异常", e);
        }
        return null;
    }

    /**
     * 从列表中获取1个随机元素
     *
     * @param elementList 列表
     * @param <T>         泛型
     * @return 1个随机元素
     */
    public static <T> T getRandomElement(List<T> elementList) {
        if (CollectionUtils.isEmpty(elementList)) {
            return null;
        }
        int index = RandomUtils.nextInt(0, elementList.size());
        return elementList.get(index);
    }

    /**
     * 从列表中获取n个随机元素组成字符串
     *
     * @param elementList 列表
     * @param n           数量
     * @return n个随机元素组成的字符串
     */
    public static String getRandomString(List<String> elementList, int n) {
        if (CollectionUtils.isEmpty(elementList) || n < 1) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int index = RandomUtils.nextInt(0, elementList.size());
            sb.append(elementList.get(index));
        }
        return sb.toString();
    }
}
