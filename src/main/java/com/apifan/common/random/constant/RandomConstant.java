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
    public static final List<String> specialCharList = Lists.newArrayList("!", ".", "_", "@", "#", "$", "%", "^", "&", ",", "(", ")", "`", "[", "]", "*");

    /**
     * 手机号前缀
     */
    public static final List<String> mobilePrefixList = Lists.newArrayList("13", "147", "15", "16", "17", "18", "19");

    /**
     * 域名后缀
     */
    public static final List<String> domainSuffixList = Lists.newArrayList("com", "org", "net", "cn", "io", "im", "info", "mobi", "biz", "pro");

}
