package com.apifan.common.random.constant;

/**
 * 省/直辖市/自治区/特别行政区名称枚举
 *
 * @author yin
 * @since 1.0.19
 */
public enum Province {

    BJ("北京市"),
    SH("上海市"),
    TJ("天津市"),
    CQ("重庆市"),
    HE("河北省"),
    SX("山西省"),
    NM("内蒙古自治区"),
    LN("辽宁省"),
    JL("吉林省"),
    HL("黑龙江省"),
    JS("江苏省"),
    ZJ("浙江省"),
    AH("安徽省"),
    FJ("福建省"),
    JX("江西省"),
    SD("山东省"),
    HA("河南省"),
    HB("湖北省"),
    HN("湖南省"),
    GD("广东省"),
    GX("广西壮族自治区"),
    HI("海南省"),
    SC("四川省"),
    GZ("贵州省"),
    YN("云南省"),
    XZ("西藏自治区"),
    SN("陕西省"),
    GS("甘肃省"),
    QH("青海省"),
    NX("宁夏回族自治区"),
    XJ("新疆维吾尔自治区"),
    TW("台湾省"),
    HK("香港特别行政区"),
    MO("澳门特别行政区");

    private String name;

    Province(String n){
        this.name = n;
    }

    public String getName(){
        return this.name;
    }
}
