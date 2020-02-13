package com.apifan.common.random.source;

import com.apifan.common.random.entity.Area;
import com.apifan.common.random.util.ResourceUtils;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 地区数据源
 *
 * @author yin
 */
public class AreaSource {
    private static final Logger logger = LoggerFactory.getLogger(AreaSource.class);

    /**
     * 地区列表
     */
    private List<Area> areaList = new ArrayList<>();

    /**
     * 中国大陆常见道路名称
     */
    private List<String> roadList = new ArrayList<>();

    /**
     * 道路名称中常见的方向
     */
    private List<String> directionList = Lists.newArrayList("东", "西", "南", "北", "中");

    private static final AreaSource instance = new AreaSource();

    private AreaSource() {
        try {
            List<String> areaLines = ResourceUtils.readLines("area.csv");
            if (CollectionUtils.isNotEmpty(areaLines)) {
                areaLines.forEach(i -> {
                    if (StringUtils.isEmpty(i)) {
                        return;
                    }
                    List<String> row = Splitter.on(",").splitToList(i);
                    Area area = new Area();
                    area.setProvince(row.get(0));
                    area.setCity(row.get(1));
                    area.setCounty(row.get(2));
                    area.setZipCode(row.get(3));
                    areaList.add(area);
                });
            }
            roadList = ResourceUtils.readLines("address-road-cn.txt");
        } catch (Exception e) {
            logger.error("初始化数据异常", e);
        }
    }

    /**
     * 获取唯一实例
     *
     * @return 实例
     */
    public static AreaSource getInstance() {
        return instance;
    }

    /**
     * 获取随机的地区信息
     *
     * @return 随机的地区信息
     */
    public Area nextArea() {
        return ResourceUtils.getRandomElement(areaList);
    }

    /**
     * 随机省级行政区名称
     *
     * @return 随机省级行政区名称
     */
    public String randomProvince() {
        return nextArea().getProvince();
    }

    /**
     * 随机城市名称
     *
     * @param separator 分隔符
     * @return 随机城市名称
     */
    public String randomCity(String separator) {
        Area area = nextArea();
        return area.getProvince() + Objects.toString(separator, "") + area.getCity();
    }

    /**
     * 随机邮编
     *
     * @return 随机邮编
     */
    public String randomZipCode() {
        return nextArea().getZipCode();
    }

    /**
     * 获取随机的详细地址
     *
     * @return 随机的详细地址
     */
    public String randomAddress() {
        Area area = nextArea();
        String prefix = area.getProvince() + area.getCity() + Objects.toString(area.getCounty(), "");
        String road = ResourceUtils.getRandomElement(roadList) + ResourceUtils.getRandomElement(directionList);
        return prefix + road + "路" + RandomUtils.nextInt(1, 1000) + "号";
    }
}
