package com.apifan.common.random.entity;

import java.io.Serializable;

/**
 * 国家或地区编码信息
 *
 * 基于 ISO 3166 标准并补充了中文名称
 *
 * @author yin
 */
public class CountryOrRegionCode implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 中文名称
     */
    private String nameCN;

    /**
     * 英文名称
     */
    private String nameEN;

    /**
     * 二位字母编码
     */
    private String alpha2;

    /**
     * 三位字母编码
     */
    private String alpha3;

    /**
     * 数字编码
     */
    private String number;

    public String getNameCN() {
        return nameCN;
    }

    public void setNameCN(String nameCN) {
        this.nameCN = nameCN;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getAlpha2() {
        return alpha2;
    }

    public void setAlpha2(String alpha2) {
        this.alpha2 = alpha2;
    }

    public String getAlpha3() {
        return alpha3;
    }

    public void setAlpha3(String alpha3) {
        this.alpha3 = alpha3;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
