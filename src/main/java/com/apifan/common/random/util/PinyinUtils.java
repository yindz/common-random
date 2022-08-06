package com.apifan.common.random.util;

import com.apifan.common.random.util.pinyin.PinyinConverter;
import com.apifan.common.random.util.pinyin.impl.Pinyin4jConverter;
import com.apifan.common.random.util.pinyin.impl.TinyPinyinConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 拼音工具类
 * <p>
 * 从 1.0.16 起，不再强依赖 tinypinyin; <br>
 * 支持目前常用的以下2种第三方库: tinypinyin/pinyin4j <br>
 * 检测顺序：tinypinyin>pinyin4j <br>
 * 注意: 不要忘记手动添加依赖
 * </p>
 *
 * @author yin
 * @since 1.0.16
 */
public class PinyinUtils {
    private static final Logger log = LoggerFactory.getLogger(PinyinUtils.class);

    private static final PinyinConverter pinyinConverter;

    static {
        pinyinConverter = getRealPinyinConverter();
    }

    /**
     * 转换成拼音
     *
     * @param src         原始字符串
     * @param toLowerCase 是否转换为小写
     * @return 拼音
     */
    public static String toPinyin(String src, boolean toLowerCase) {
        return pinyinConverter.toPinyin(src, toLowerCase);
    }

    /**
     * 获取拼音转换器实例
     *
     * @return 拼音转换器实例
     */
    private static PinyinConverter getRealPinyinConverter() {
        if (ResourceUtils.isClassLoaded("com.github.promeg.pinyinhelper.Pinyin")) {
            log.info("将使用 tinypinyin");
            return new TinyPinyinConverter();
        } else if (ResourceUtils.isClassLoaded("net.sourceforge.pinyin4j.PinyinHelper")) {
            log.info("将使用 pinyin4j");
            return new Pinyin4jConverter();
        } else {
            throw new RuntimeException("没有找到可用的拼音库");
        }
    }
}
