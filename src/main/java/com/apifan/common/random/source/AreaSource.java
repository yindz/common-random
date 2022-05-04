package com.apifan.common.random.source;

import com.apifan.common.random.entity.Area;
import com.apifan.common.random.entity.CountryOrRegionCode;
import com.apifan.common.random.util.ResourceUtils;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 地区数据源
 *
 * @author yin
 */
public class AreaSource {
    private static final Logger logger = LoggerFactory.getLogger(AreaSource.class);

    private static final Pattern ALPHA_1 = Pattern.compile("[a-zA-Z]");

    /**
     * 地区列表
     */
    private List<Area> areaList = new ArrayList<>();

    /**
     * 道路名称中常见的方向
     */
    private List<String> directionList = Lists.newArrayList("东", "西", "南", "北", "中");

    /**
     * 中国大陆常见小区名称
     */
    private List<String> communityNameList = new ArrayList<>();

    /**
     * 中国大陆常见小区名称后缀
     */
    private List<String> communitySuffixList = new ArrayList<>();

    /**
     * 地名常用词
     */
    private List<String> addressWordList = new ArrayList<>();

    /**
     * 乡镇
     */
    private List<String> townSuffixList = Lists.newArrayList("乡", "镇");

    /**
     * 区号
     */
    private Map<String, List<String>> phoneCodeMap = new HashMap<>();

    /**
     * 国家或地区编码信息列表
     */
    private List<CountryOrRegionCode> countryOrRegionCodeList = new ArrayList<>();

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
            communityNameList = ResourceUtils.readLines("community-name.txt");
            communitySuffixList = ResourceUtils.readLines("community-suffix.txt");
            addressWordList = ResourceUtils.readLines("address-word-cn.txt");
            List<Map<String, Object>> phoneCodeMapList = ResourceUtils.readAsMapList("phone-code.json");
            if (CollectionUtils.isNotEmpty(phoneCodeMapList)) {
                phoneCodeMapList.forEach(p -> {
                    String areaName = Objects.toString(p.get("area"));
                    List<String> codeList = (List<String>) p.get("code");
                    phoneCodeMap.put(areaName, codeList);
                });
            }
            List<String> countryOrRegionCodes = ResourceUtils.base64DecodeLines(ResourceUtils.readLines("iso-3166-1.txt"));
            countryOrRegionCodes.forEach(c -> {
                if (StringUtils.isBlank(c)) {
                    return;
                }
                String[] tmp = c.split(",");
                if (tmp.length != 5) {
                    return;
                }
                CountryOrRegionCode regionCode = new CountryOrRegionCode();
                regionCode.setNameEN(tmp[0]);
                regionCode.setAlpha2(tmp[1]);
                regionCode.setAlpha3(tmp[2]);
                regionCode.setNumber(tmp[3]);
                regionCode.setNameCN(tmp[4]);
                countryOrRegionCodeList.add(regionCode);
            });
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
        if (prefix.endsWith("县") || prefix.endsWith("旗")) {
            //乡村地址
            String town = ResourceUtils.getRandomString(addressWordList, 2) + ResourceUtils.getRandomElement(townSuffixList);
            String village = ResourceUtils.getRandomString(addressWordList, 2) + "村";
            String group = ResourceUtils.getRandomString(addressWordList, 2) + "组";
            return prefix + town + village + group + RandomUtils.nextInt(1, 100) + "号";
        } else {
            //城镇地址
            String road = ResourceUtils.getRandomString(addressWordList, 2) + ResourceUtils.getRandomElement(directionList);
            String community = ResourceUtils.getRandomElement(communityNameList) + ResourceUtils.getRandomElement(communitySuffixList);
            String extra = "";
            int x = NumberSource.getInstance().randomInt(0, 11);
            if (x % 3 == 0) {
                extra = ResourceUtils.getRandomElement(directionList);
            }
            String building = RandomUtils.nextInt(1, 20) + "栋";
            String unit = RandomUtils.nextInt(1, 5) + "单元";
            String room = String.format("%02d", RandomUtils.nextInt(1, 31)) + String.format("%02d", RandomUtils.nextInt(1, 5)) + "房";
            return prefix + road + "路" + RandomUtils.nextInt(1, 1000) + "号" + community + extra + building + unit + room;
        }
    }

    /**
     * 随机纬度(中国)
     *
     * @return 随机纬度
     */
    public double randomLatitude() {
        return NumberSource.getInstance().randomDouble(3.86D, 53.55D);
    }

    /**
     * 随机经度(中国)
     *
     * @return 随机经度
     */
    public double randomLongitude() {
        return NumberSource.getInstance().randomDouble(73.66D, 135.05D);
    }

    /**
     * 随机固话区号
     *
     * @param province 省份
     * @return 随机固话区号
     */
    public String randomPhoneCode(String province) {
        if (StringUtils.isBlank(province)) {
            return null;
        }
        province = province.replace("省", "").replace("市", "").replace("自治区", "");
        if (!phoneCodeMap.containsKey(province)) {
            throw new IllegalArgumentException("暂不存在省份名称 " + province + " 对应的区号数据!");
        }
        List<String> codeList = phoneCodeMap.get(province);
        return CollectionUtils.isNotEmpty(codeList) ? codeList.get(RandomUtils.nextInt(0, codeList.size())) : null;
    }

    /**
     * 随机固话号码
     *
     * @param province  省份
     * @param delimiter 区号和号码之间的分隔符
     * @return 随机固话号码
     */
    public String randomPhoneNumber(String province, String delimiter) {
        String prefix = randomPhoneCode(province);
        if (StringUtils.isBlank(province)) {
            return null;
        }
        if (delimiter == null) {
            //默认分隔符
            delimiter = " ";
        }
        return prefix + delimiter + RandomUtils.nextLong(10000000L, 99999999L);
    }

    /**
     * 随机国家或地区编码信息
     *
     * @param startsWith 首字母(不区分大小写)
     * @return 随机的国家或地区编码信息
     */
    public CountryOrRegionCode randomCountryOrRegionCode(String startsWith) {
        Preconditions.checkArgument(ALPHA_1.matcher(startsWith).matches(), "startsWith 必须为单个字母");
        List<CountryOrRegionCode> filteredList = countryOrRegionCodeList.stream()
                .filter(i -> i.getAlpha2().startsWith(startsWith.toUpperCase()) || i.getAlpha3().startsWith(startsWith.toUpperCase())).collect(Collectors.toList());
        return randomCountryOrRegionCode(filteredList);
    }

    /**
     * 随机国家或地区编码信息(不限首字母)
     *
     * @return 随机的国家或地区编码信息
     */
    public CountryOrRegionCode randomCountryOrRegionCode() {
        return randomCountryOrRegionCode(countryOrRegionCodeList);
    }

    /**
     * 返回随机的国家或地区编码信息
     *
     * @param list 原始列表
     * @return 随机的国家或地区编码信息
     */
    private CountryOrRegionCode randomCountryOrRegionCode(List<CountryOrRegionCode> list) {
        return CollectionUtils.isNotEmpty(list) ? list.get(RandomUtils.nextInt(0, list.size() - 1)) : null;
    }
}
