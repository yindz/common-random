package com.apifan.common.random.util;

import java.io.Serializable;

/**
 * 地区信息
 *
 * @author yin
 */
public class Area implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 省级行政区
     */
    private String province;

    /**
     * 地级行政区
     */
    private String city;

    /**
     * 县级行政区
     */
    private String county;

    /**
     * 邮编
     */
    private String zipCode;

    /**
     * 获取 省级行政区
     *
     * @return province 省级行政区
     */
    public String getProvince() {
        return this.province;
    }

    /**
     * 设置 省级行政区
     *
     * @param province 省级行政区
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取 地级行政区
     *
     * @return city 地级行政区
     */
    public String getCity() {
        return this.city;
    }

    /**
     * 设置 地级行政区
     *
     * @param city 地级行政区
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取 县级行政区
     *
     * @return county 县级行政区
     */
    public String getCounty() {
        return this.county;
    }

    /**
     * 设置 县级行政区
     *
     * @param county 县级行政区
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * 获取 邮编
     *
     * @return zipCode 邮编
     */
    public String getZipCode() {
        return this.zipCode;
    }

    /**
     * 设置 邮编
     *
     * @param zipCode 邮编
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
