package io.github.yindz.random.constant;

/**
 * 足球联合会
 *
 * @author yin
 */
public enum FootballConfederation {

    AFC("亚洲足联"),
    CAF("非洲足联"),
    CONCACAF("中北美及加勒比海地区足联"),
    CONMEBOL("南美洲足联"),
    OFC("大洋洲足联"),
    UEFA("欧洲足联");

    private String name;

    private FootballConfederation(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
