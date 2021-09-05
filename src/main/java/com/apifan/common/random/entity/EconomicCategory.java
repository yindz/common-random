package com.apifan.common.random.entity;

import java.io.Serializable;

/**
 * 国民经济行业分类信息
 *
 * @author yin
 */
public class EconomicCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
