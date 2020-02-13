package com.apifan.common.random.util;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.Resources;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;

/**
 * 资源工具
 *
 * @author yin
 */
public class ResourceUtils {

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
}
