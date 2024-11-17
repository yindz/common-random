package io.github.yindz.random.entity;

import java.io.Serializable;

/**
 * 身份证前缀信息
 *
 * @author yin
 */
public class IdPrefix implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 身份证前缀
     */
    private String prefix;

    /**
     * 地理位置
     */
    private String location;

    /**
     * 父节点
     */
    private String parent;

    /**
     * 获取 身份证前缀
     *
     * @return prefix 身份证前缀
     */
    public String getPrefix() {
        return this.prefix;
    }

    /**
     * 设置 身份证前缀
     *
     * @param prefix 身份证前缀
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * 获取 地理位置
     *
     * @return location 地理位置
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * 设置 地理位置
     *
     * @param location 地理位置
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 获取 父节点
     *
     * @return parent 父节点
     */
    public String getParent() {
        return this.parent;
    }

    /**
     * 设置 父节点
     *
     * @param parent 父节点
     */
    public void setParent(String parent) {
        this.parent = parent;
    }
}
