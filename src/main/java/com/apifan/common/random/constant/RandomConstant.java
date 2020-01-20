package com.apifan.common.random.constant;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 常量
 *
 * @author yin
 */
public class RandomConstant {
    /**
     * 常用特殊字符
     */
    public static final List<String> specialCharList = Lists.newArrayList("!", ".", "_", "@", "#", "$", "%", "^", "&", ",", "(", ")", "`", "[", "]");

    /**
     * 手机号前缀
     */
    public static final List<String> mobilePrefixList = Lists.newArrayList("13", "147", "15", "16", "17", "18", "19");

    /**
     * 域名后缀
     */
    public static final List<String> domainSuffixList = Lists.newArrayList("com", "org", "net", "cn", "io", "im", "info", "mobi", "biz", "pro");

    /**
     * 省份前缀
     */
    public static final List<String> provincePrefixList = Lists.newArrayList(
            "京", "津", "冀", "晋", "蒙",
            "辽", "吉", "黑", "沪", "苏",
            "浙", "皖", "闽", "赣", "鲁",
            "豫", "鄂", "湘", "粤", "桂",
            "琼", "渝", "川", "贵", "云",
            "藏", "陕", "甘", "宁", "青", "新");

    /**
     * 车牌号码候选字母(无I/O)
     */
    public static final List<String> plateNumbersList = Lists.newArrayList(
            "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
}
