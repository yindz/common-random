package com.github.yindz.random.entity;

import java.io.Serializable;

/**
 * 货币信息
 *
 * @author yin
 */
public class CurrencyInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 国家或地区
     */
    private String region;

    /**
     * 货币名称
     */
    private String name;

    /**
     * 货币编码
     */
    private String code;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
