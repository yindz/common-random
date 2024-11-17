package com.github.yindz.random.source;

import com.github.yindz.random.constant.CompetitionType;
import com.github.yindz.random.constant.FootballConfederation;
import com.github.yindz.random.entity.FootballTeam;
import com.github.yindz.random.util.ResourceUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    /**
     * 篮球-NBA
     */
    private static List<String> BASKETBALL_NBA = Lists.newArrayList();

    /**
     * 篮球-CBA
     */
    private static List<String> BASKETBALL_CBA = Lists.newArrayList();

    /**
     * FIFA成员列表
     */
    private static final List<FootballTeam> FIFA_MEMBER_LIST = Lists.newArrayList();

    private static final SportSource instance = new SportSource();

    private SportSource() {
        FOOTBALL_PREMIER_LEAGUE = ResourceUtils.readLines("football-premier-league.txt");
        FOOTBALL_LA_LIGA = ResourceUtils.readLines("football-la-liga.txt");
        FOOTBALL_BUNDESLIGA = ResourceUtils.readLines("football-bundesliga.txt");
        FOOTBALL_SERIE_A = ResourceUtils.readLines("football-serie-a.txt");
        FOOTBALL_LIGUE_1 = ResourceUtils.readLines("football-ligue-1.txt");
        FOOTBALL_EREDIVISIE = ResourceUtils.readLines("football-eredivisie.txt");
        BASKETBALL_NBA = ResourceUtils.readLines("basketball-nba.txt");
        BASKETBALL_CBA = ResourceUtils.readLines("basketball-cba.txt");
        List<String> fifaMemberInfoList = ResourceUtils.readLines("fifa-members.txt");
        if (CollectionUtils.isNotEmpty(fifaMemberInfoList)) {
            fifaMemberInfoList.forEach(e -> {
                if (StringUtils.isBlank(e)) {
                    return;
                }
                String[] tmp = e.split(",");
                if (tmp.length == 5) {
                    FootballTeam fm = new FootballTeam();
                    fm.setName(tmp[0]);
                    fm.setNameEn(tmp[1]);
                    fm.setCode(tmp[2]);
                    fm.setConfederation(tmp[3]);
                    fm.setConfederationName(tmp[4]);
                    FIFA_MEMBER_LIST.add(fm);
                }
            });
        }
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

    /**
     * 获取随机的篮球联赛球队名称
     *
     * @param competition 体育竞技赛事类型
     * @return 随机的篮球联赛球队名称
     */
    public String randomBasketballTeam(CompetitionType competition) {
        Preconditions.checkArgument(competition != null, "必须传入篮球联赛类型");
        if (CompetitionType.NBA.equals(competition)) {
            return ResourceUtils.getRandomElement(BASKETBALL_NBA);
        } else if (CompetitionType.CBA.equals(competition)) {
            return ResourceUtils.getRandomElement(BASKETBALL_CBA);
        } else {
            throw new RuntimeException("未知的篮球联赛类型");
        }
    }

    /**
     * 获取随机的国家及地区的足球代表队信息
     *
     * @param confederation 所属足球联合会
     * @return 随机的国家及地区的足球代表队信息
     */
    public FootballTeam randomFootballTeam(FootballConfederation confederation) {
        Preconditions.checkNotNull(confederation, "请传入相应的足球联合会参数");
        return ResourceUtils.getRandomElement(FIFA_MEMBER_LIST.stream().filter(i -> Objects.equals(confederation.getName(), i.getConfederationName())).collect(Collectors.toList()));
    }

    /**
     * 获取随机的国家及地区的足球代表队信息(不限足球联合会)
     *
     * @return 随机的国家及地区的足球代表队信息
     */
    public FootballTeam randomFootballTeam() {
        return ResourceUtils.getRandomElement(FIFA_MEMBER_LIST);
    }
}
