package io.github.yindz.random.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 农历日期
 *
 * @author yin
 * @since 1.0.22
 */
public class ChineseLunarDate implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final Map<String, String> NUMBERS = new HashMap<>();

    static {
        NUMBERS.put("1", "一");
        NUMBERS.put("2", "二");
        NUMBERS.put("3", "三");
        NUMBERS.put("4", "四");
        NUMBERS.put("5", "五");
        NUMBERS.put("6", "六");
        NUMBERS.put("7", "七");
        NUMBERS.put("8", "八");
        NUMBERS.put("9", "九");
        NUMBERS.put("10", "十");
    }

    /**
     * 年
     */
    private String year;

    /**
     * 月
     */
    private String month;

    /**
     * 日
     */
    private String day;

    /**
     * 转换
     */
    public void convert() {
        //转换月份
        if (this.month != null) {
            String prefix = "";
            if (this.month.startsWith("R")) {
                prefix = "闰";
                this.month = this.month.replace("R", "");
            }
            int monthNum = Integer.parseInt(this.month);
            if (monthNum == 1) {
                this.month = prefix + "正月";
            } else if (monthNum <= 10) {
                this.month = prefix + NUMBERS.get(this.month) + "月";
            } else {
                this.month = prefix + "十" + NUMBERS.get(String.valueOf(monthNum % 10)) + "月";
            }
        }

        //转换日期
        if (this.day != null) {
            int dayNum = Integer.parseInt(this.day);
            if (dayNum == 20) {
                this.day = "二十";
                return;
            }
            if (dayNum == 30) {
                this.day = "三十";
                return;
            }
            if (dayNum <= 10) {
                this.day = "初" + NUMBERS.get(this.day);
                return;
            }
            int lastNum = dayNum % 10;
            if (dayNum < 20) {
                this.day = "十" + NUMBERS.get(String.valueOf(lastNum));
                return;
            }
            if (dayNum < 30) {
                this.day = "廿" + NUMBERS.get(String.valueOf(lastNum));
            }
        }
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
