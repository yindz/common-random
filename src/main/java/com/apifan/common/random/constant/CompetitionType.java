package com.apifan.common.random.constant;

/**
 * 体育竞技赛事类型
 *
 * @author yin
 */
public enum CompetitionType {
    PREMIER_LEAGUE("英超"),
    LA_LIGA("西甲"),
    BUNDESLIGA("德甲"),
    SERIE_A("意甲"),
    LIGUE_1("法甲"),
    EREDIVISIE("荷甲");

    private String name;

    private CompetitionType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
