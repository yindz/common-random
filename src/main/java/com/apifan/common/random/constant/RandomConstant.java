package com.apifan.common.random.constant;

import com.google.common.collect.Lists;

import java.awt.*;
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

    /**
     * 默认字体
     */
    public static final Font defaultFont = new Font("Dialog", Font.PLAIN, 78);

    /**
     * 姓名图片可选颜色
     */
    public static final List<String> namePictureColorsList = Lists.newArrayList(
            "255,111,97", "107,91,149", "136,176,75", "146,168,209", "149,82,81"
            , "181,101,167", "0,155,119", "221,65,36", "214,80,118", "68,184,172"
            , "239,192,80", "91,94,166", "155,35,53", "223,207,190", "85,180,176"
            , "225,93,68", "127,205,205", "188,36,60", "195,68,122", "152,180,212");

    /**
     * 教育程度
     */
    public static final List<String> degreeList = Lists.newArrayList("小学", "初中", "中专/职校", "高中", "专科", "本科", "硕士", "博士");
}
