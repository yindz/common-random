package com.apifan.common.random.source;

import com.apifan.common.random.util.ResourceUtils;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 其它杂项数据源
 *
 * @author yin
 */
public class OtherSource {
    private static final Logger logger = LoggerFactory.getLogger(OtherSource.class);

    /**
     * 车牌号码候选字母(无I/O)
     */
    private static final List<String> plateNumbersList = Lists.newArrayList(
            "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    /**
     * 省份前缀
     */
    public static final List<String> provincePrefixList = Lists.newArrayList(
            "京", "津", "冀", "晋", "蒙",
            "辽", "吉", "黑", "沪", "苏",
            "浙", "皖", "闽", "赣", "鲁",
            "豫", "鄂", "湘", "粤", "桂",
            "琼", "渝", "川", "贵", "云",
            "藏", "陕", "甘", "宁", "青", "新");

    /**
     * 公司后缀
     */
    public static final List<String> companySuffixList = Lists.newArrayList("股份有限公司", "有限责任公司");

    /**
     * 公司行业
     */
    public static final List<String> companyIndustryList = Lists.newArrayList("科技", "信息", "商贸", "贸易",
            "实业", "文化传播", "文化创意", "工程", "教育", "发展", "咨询", "设计", "置业", "投资");

    /**
     * 中文名词
     */
    public static List<String> chineseNounsList = Lists.newArrayList();

    /**
     * 中文动词
     */
    public static List<String> chineseVerbsList = Lists.newArrayList();

    /**
     * 中文副词
     */
    public static List<String> chineseAdverbsList = Lists.newArrayList();

    /**
     * 中文代词
     */
    public static List<String> chinesePronounsList = Lists.newArrayList();

    /**
     * 中文连词
     */
    public static List<String> chineseConjunctionsList = Lists.newArrayList();

    private static final OtherSource instance = new OtherSource();

    private OtherSource() {
        chineseNounsList = ResourceUtils.readLines("common-chinese-nouns.txt");
        chinesePronounsList = ResourceUtils.readLines("common-chinese-pronouns.txt");
        chineseAdverbsList = ResourceUtils.readLines("common-chinese-adverbs.txt");
        chineseVerbsList = ResourceUtils.readLines("common-chinese-verbs.txt");
        chineseConjunctionsList = ResourceUtils.readLines("common-chinese-conjunctions.txt");
    }

    /**
     * 获取唯一实例
     *
     * @return 实例
     */
    public static OtherSource getInstance() {
        return instance;
    }

    /**
     * 获取随机的1个汉字
     *
     * @return 随机的1个汉字
     */
    public String randomChinese() {
        String str = "";
        int highCode = RandomUtils.nextInt(176, 215), lowCode = RandomUtils.nextInt(161, 254);
        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(highCode)).byteValue();
        b[1] = (Integer.valueOf(lowCode)).byteValue();
        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            logger.error("发生编码解析异常", e);
        }
        return str;
    }

    /**
     * 获取随机N个汉字
     *
     * @param count 数量
     * @return 随机的N个汉字
     */
    public String randomChinese(int count) {
        if (count < 1) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(randomChinese());
        }
        return sb.toString();
    }

    /**
     * 生成随机的中国大陆车牌号
     *
     * @param isNewEnergyVehicle 是否为新能源车型
     * @return 随机的中国大陆车牌号
     */
    public String randomPlateNumber(boolean isNewEnergyVehicle) {
        int length = 5;
        List<String> plateNumbers = new ArrayList<>(length);
        String prefix = ResourceUtils.getRandomElement(provincePrefixList);
        //最多2个字母
        int alphaCnt = RandomUtils.nextInt(0, 3);
        if (alphaCnt > 0) {
            for (int i = 0; i < alphaCnt; i++) {
                plateNumbers.add(ResourceUtils.getRandomElement(plateNumbersList));
            }
        }
        //剩余部分全是数字
        int numericCnt = length - alphaCnt;
        for (int i = 0; i < numericCnt; i++) {
            plateNumbers.add(String.valueOf(RandomUtils.nextInt(0, 10)));
        }
        //打乱顺序
        Collections.shuffle(plateNumbers);

        String newEnergyVehicleTag = "";
        if (isNewEnergyVehicle) {
            int j = RandomUtils.nextInt(0, 2);
            //新能源车牌前缀为D或F
            newEnergyVehicleTag = (j == 0 ? "D" : "F");
        }
        return prefix + ResourceUtils.getRandomElement(plateNumbersList)
                + newEnergyVehicleTag + Joiner.on("").join(plateNumbers);
    }

    /**
     * 生成随机的中国大陆车牌号(非新能源车型)
     *
     * @return 随机的中国大陆车牌号
     */
    public String randomPlateNumber() {
        return randomPlateNumber(false);
    }

    /**
     * 随机公司名称
     *
     * @param province 省份
     * @return 随机公司名称
     */
    public String randomCompanyName(String province) {
        int length = RandomUtils.nextInt(2, 7);
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotEmpty(province)) {
            sb.append(province);
        }
        sb.append(randomChinese(length));
        sb.append(ResourceUtils.getRandomElement(companyIndustryList));
        sb.append(ResourceUtils.getRandomElement(companySuffixList));
        return sb.toString();
    }

    /**
     * 随机中文句子
     *
     * @return 随机中文句子
     */
    public String randomChineseSentence() {
        StringBuilder sb = new StringBuilder();
        sb.append(ResourceUtils.getRandomElement(chinesePronounsList));
        sb.append(ResourceUtils.getRandomElement(chineseNounsList));
        sb.append(ResourceUtils.getRandomElement(chineseAdverbsList));
        sb.append(ResourceUtils.getRandomElement(chineseVerbsList));
        sb.append(ResourceUtils.getRandomElement(chineseNounsList));
        int r = RandomUtils.nextInt(1, 11);
        if (r % 2 == 0) {
            sb.append(ResourceUtils.getRandomElement(chineseNounsList));
        }
        r = RandomUtils.nextInt(1, 11);
        if (r % 2 == 0) {
            sb.append("，");
            sb.append(ResourceUtils.getRandomElement(chineseConjunctionsList));
            r = RandomUtils.nextInt(1, 11);
            if (r % 3 == 0) {
                sb.append(ResourceUtils.getRandomElement(chinesePronounsList));
            }
            sb.append(ResourceUtils.getRandomElement(chineseNounsList));
            sb.append(ResourceUtils.getRandomElement(chineseVerbsList));
            sb.append(ResourceUtils.getRandomElement(chineseNounsList));
            r = RandomUtils.nextInt(1, 11);
            if (r % 3 == 0) {
                sb.append(ResourceUtils.getRandomElement(chineseNounsList));
            }
        }
        sb.append("。");
        return sb.toString();
    }

    /**
     * 随机RGB颜色值
     *
     * @return 随机RGB颜色值
     */
    public int[] randomRgbColor() {
        return new int[]{RandomUtils.nextInt(0, 256), RandomUtils.nextInt(0, 256), RandomUtils.nextInt(0, 256)};
    }

    /**
     * 随机HEX颜色值
     *
     * @return 随机HEX颜色值
     */
    public String randomHexColor() {
        int[] color = randomRgbColor();
        return String.format("#%02x%02x%02x", color[0], color[1], color[2]).toUpperCase();
    }
}
