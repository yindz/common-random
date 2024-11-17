package io.github.yindz.random;

import io.github.yindz.random.source.*;

/**
 * 统一入口类
 *
 * @author yin
 * @since 1.0.19
 */
public class RandomSource {

    /**
     * 获取地区数据源
     *
     * @return 地区数据源
     */
    public static AreaSource areaSource() {
        return AreaSource.getInstance();
    }

    /**
     * 获取日期时间数据源
     *
     * @return 日期时间数据源
     */
    public static DateTimeSource dateTimeSource() {
        return DateTimeSource.getInstance();
    }

    /**
     * 获取教育信息数据源
     *
     * @return 教育信息数据源
     */
    public static EducationSource educationSource() {
        return EducationSource.getInstance();
    }

    /**
     * 获取金融相关数据源
     *
     * @return 金融相关数据源
     */
    public static FinancialSource financialSource() {
        return FinancialSource.getInstance();
    }

    /**
     * 获取互联网信息数据源
     *
     * @return 互联网信息数据源
     */
    public static InternetSource internetSource() {
        return InternetSource.getInstance();
    }

    /**
     * 获取数值数据源
     *
     * @return 数值数据源
     */
    public static NumberSource numberSource() {
        return NumberSource.getInstance();
    }

    /**
     * 获取个人信息数据源
     *
     * @return 个人信息数据源
     */
    public static PersonInfoSource personInfoSource() {
        return PersonInfoSource.getInstance();
    }

    /**
     * 获取体育竞技数据源
     *
     * @return 体育竞技数据源
     */
    public static SportSource sportSource() {
        return SportSource.getInstance();
    }

    /**
     * 获取语言文字数据源
     *
     * @return 语言文字数据源
     */
    public static LanguageSource languageSource() {
        return LanguageSource.getInstance();
    }

    /**
     * 获取其它杂项数据源
     *
     * @return 其它杂项数据源
     */
    public static OtherSource otherSource() {
        return OtherSource.getInstance();
    }
}
