package io.github.yindz.random.util.pinyin.impl;

import io.github.yindz.random.util.pinyin.PinyinConverter;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Pinyin4j转换器接口实现类
 *
 * @author yin
 * @since 1.0.16
 */
public class Pinyin4jConverter implements PinyinConverter {
    private static final Logger log = LoggerFactory.getLogger(Pinyin4jConverter.class);

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
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        if (toLowerCase) {
            outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        } else {
            outputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        }
        List<String> out = new ArrayList<>();
        char[] chars = src.toCharArray();
        for (char c : chars) {
            try {
                String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(c, outputFormat);
                if (pinyin.length > 0) {
                    //即使是多音字，也只取第1个读音
                    out.add(pinyin[0]);
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                log.error("转换拼音异常", e);
            }
        }
        return Joiner.on("").join(out);
    }
}
