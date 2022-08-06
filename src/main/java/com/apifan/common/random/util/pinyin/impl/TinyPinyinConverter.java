package com.apifan.common.random.util.pinyin.impl;

import com.apifan.common.random.util.pinyin.PinyinConverter;
import com.github.promeg.pinyinhelper.Pinyin;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

/**
 * TinyPinyin转换器接口实现类
 *
 * @author yin
 * @since 1.0.16
 */
public class TinyPinyinConverter implements PinyinConverter {

    /**
     * 转换成拼音
     *
     * @param src         原始字符串
     * @param toLowerCase 是否转换为小写
     * @return 拼音
     */
    @Override
    public String toPinyin(String src, boolean toLowerCase) {
        Preconditions.checkArgument(StringUtils.isNotBlank(src), "原始字符串为空");
        String result = Pinyin.toPinyin(src, "");
        return toLowerCase ? result.toLowerCase() : result;
    }
}
