package io.github.yindz.random.entity;

import java.io.Serializable;

/**
 * IP范围
 *
 * @author yin
 */
public class IpRange implements Serializable {
    private static final long serialVersionUID = 1L;

    private String beginIp;

    private long beginIpNum;

    private String endIp;

    private long endIpNum;

    public String getBeginIp() {
        return beginIp;
    }

    public void setBeginIp(String beginIp) {
        this.beginIp = beginIp;
    }

    public long getBeginIpNum() {
        return beginIpNum;
    }

    public void setBeginIpNum(long beginIpNum) {
        this.beginIpNum = beginIpNum;
    }

    public String getEndIp() {
        return endIp;
    }

    public void setEndIp(String endIp) {
        this.endIp = endIp;
    }

    public long getEndIpNum() {
        return endIpNum;
    }

    public void setEndIpNum(long endIpNum) {
        this.endIpNum = endIpNum;
    }
}
