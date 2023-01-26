package com.apifan.common.random.constant;

/**
 * 省/直辖市/自治区/特别行政区名称枚举
 *
 * @author yin
 * @since 1.0.19
 */
public enum Province {

    BJ("北京市", "京"),
    SH("上海市", "沪"),
    TJ("天津市", "津"),
    CQ("重庆市", "渝"),
    HE("河北省", "冀"),
    SX("山西省", "晋"),
    NM("内蒙古自治区", "蒙"),
    LN("辽宁省", "辽"),
    JL("吉林省", "吉"),
    HL("黑龙江省", "黑"),
    JS("江苏省", "苏"),
    ZJ("浙江省", "浙"),
    AH("安徽省", "皖"),
    FJ("福建省", "闽"),
    JX("江西省", "赣"),
    SD("山东省", "鲁"),
    HA("河南省", "豫"),
    HB("湖北省", "鄂"),
    HN("湖南省", "湘"),
    GD("广东省", "粤"),
    GX("广西壮族自治区", "桂"),
    HI("海南省", "琼"),
    SC("四川省", "川"),
    GZ("贵州省", "贵"),
    YN("云南省", "云"),
    XZ("西藏自治区", "藏"),
    SN("陕西省", "陕"),
    GS("甘肃省", "甘"),
    QH("青海省", "青"),
    NX("宁夏回族自治区", "宁"),
    XJ("新疆维吾尔自治区", "新"),
    TW("台湾省", "台"),
    HK("香港特别行政区", "港"),
    MO("澳门特别行政区", "澳");

    private String name;
    private String prefix;

    Province(String n, String p) {
        this.name = n;
        this.prefix = p;
    }

    public String getName() {
        return this.name;
    }

    public String getPrefix() {
        return this.prefix;
    }
}
