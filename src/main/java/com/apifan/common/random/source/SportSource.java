package com.apifan.common.random.source;

import com.apifan.common.random.constant.CompetitionType;
import com.apifan.common.random.util.ResourceUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 体育竞技数据源
 *
 * @author yin
 */
public class SportSource {
    private static final Logger logger = LoggerFactory.getLogger(SportSource.class);

    /**
     * 足球-英超
     */
    private static List<String> FOOTBALL_PREMIER_LEAGUE = Lists.newArrayList();

    /**
     * 足球-西甲
     */
    private static List<String> FOOTBALL_LA_LIGA = Lists.newArrayList();

    /**
     * 足球-德甲
     */
    private static List<String> FOOTBALL_BUNDESLIGA = Lists.newArrayList();

    /**
     * 足球-意甲
     */
    private static List<String> FOOTBALL_SERIE_A = Lists.newArrayList();

    /**
     * 足球-法甲
     */
    private static List<String> FOOTBALL_LIGUE_1 = Lists.newArrayList();

    /**
     * 足球-荷甲
     */
    private static List<String> FOOTBALL_EREDIVISIE = Lists.newArrayList();

    private static final SportSource instance = new SportSource();

    private SportSource() {
        FOOTBALL_PREMIER_LEAGUE = ResourceUtils.readLines("football-premier-league.txt");
        FOOTBALL_LA_LIGA = ResourceUtils.readLines("football-la-liga.txt");
        FOOTBALL_BUNDESLIGA = ResourceUtils.readLines("football-bundesliga.txt");
        FOOTBALL_SERIE_A = ResourceUtils.readLines("football-serie-a.txt");
        FOOTBALL_LIGUE_1 = ResourceUtils.readLines("football-ligue-1.txt");
        FOOTBALL_EREDIVISIE = ResourceUtils.readLines("football-eredivisie.txt");
    }

    /**
     * 获取唯一实例
     *
     * @return 实例
     */
    public static SportSource getInstance() {
        return instance;
    }

    /**
     * 获取随机的足球联赛球队名称
     *
     * @param competition 体育竞技赛事类型
     * @return 随机的足球联赛球队名称
     */
    public String randomFootballTeam(CompetitionType competition) {
        Preconditions.checkArgument(competition != null, "必须传入足球联赛类型");
        if (CompetitionType.PREMIER_LEAGUE.equals(competition)) {
            return ResourceUtils.getRandomElement(FOOTBALL_PREMIER_LEAGUE);
        } else if (CompetitionType.LA_LIGA.equals(competition)) {
            return ResourceUtils.getRandomElement(FOOTBALL_LA_LIGA);
        } else if (CompetitionType.BUNDESLIGA.equals(competition)) {
            return ResourceUtils.getRandomElement(FOOTBALL_BUNDESLIGA);
        } else if (CompetitionType.SERIE_A.equals(competition)) {
            return ResourceUtils.getRandomElement(FOOTBALL_SERIE_A);
        } else if (CompetitionType.LIGUE_1.equals(competition)) {
            return ResourceUtils.getRandomElement(FOOTBALL_LIGUE_1);
        } else if (CompetitionType.EREDIVISIE.equals(competition)) {
            return ResourceUtils.getRandomElement(FOOTBALL_EREDIVISIE);
        } else {
            throw new RuntimeException("未知的足球联赛类型");
        }
    }
}
