package io.github.yindz.random.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * K线数据
 *
 * @author yin
 */
public class KChartData implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 日期(yyyyMMdd)
     */
    private String date;

    /**
     * 开盘价
     */
    private BigDecimal open;

    /**
     * 最高价
     */
    private BigDecimal high;

    /**
     * 收盘价
     */
    private BigDecimal close;

    /**
     * 最低价
     */
    private BigDecimal low;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }
}
