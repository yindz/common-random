package com.github.yindz.random.entity;

import java.io.Serializable;

/**
 * 国家及地区的足球代表队
 *
 * <p>
 *     1.国际足球联合会(Fédération Internationale de Football Association)简称国际足联(FIFA)，是管理足球的国际体育组织；<br>
 *     2.共有6个被FIFA认证的各大洲足球联合会: {@link com.github.yindz.random.constant.FootballConfederation} <br>
 *     3.国际足联旗下的成员不全是主权国家，也有部分特殊地区 <br>
 * </p>
 *
 * @author yin
 */
public class FootballTeam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 中文名称
     */
    private String name;

    /**
     * 英文名称
     */
    private String nameEn;

    /**
     * 编码
     */
    private String code;

    /**
     * 所属足球联合会编码
     */
    private String confederation;

    /**
     * 所属足球联合会中文名称
     */
    private String confederationName;

    @Override
    public String toString() {
        return this.name + "," + this.nameEn + "," + this.code + "," + this.confederation + "," + this.confederationName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getConfederation() {
        return confederation;
    }

    public void setConfederation(String confederation) {
        this.confederation = confederation;
    }

    public String getConfederationName() {
        return confederationName;
    }

    public void setConfederationName(String confederationName) {
        this.confederationName = confederationName;
    }
}
