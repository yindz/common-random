package com.github.yindz.random.util.pinyin;

/**
 * 拼音转换器接口
 *
 * @author yin
 * @since 1.0.16
 */
public interface PinyinConverter {

    /**
     * 转换成拼音
     *
     * @param src         原始字符串
     * @param toLowerCase 是否转换为小写
     * @return 拼音
     */
    String toPinyin(String src, boolean toLowerCase);
}
