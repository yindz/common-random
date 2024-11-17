package io.github.yindz.random.source;

import io.github.yindz.random.constant.Province;
import io.github.yindz.random.entity.Area;
import io.github.yindz.random.entity.EconomicCategory;
import io.github.yindz.random.entity.Poem;
import io.github.yindz.random.util.ResourceUtils;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
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
    private static final List<String> provincePrefixList = Lists.newArrayList();

    /**
     * 公司后缀
     */
    private static final List<String> companySuffixList = Lists.newArrayList("股份有限公司", "有限责任公司");

    /**
     * 公司行业
     */
    private static final List<String> companyIndustryList = Lists.newArrayList("科技", "信息", "商贸", "贸易",
            "实业", "文化传播", "文化创意", "工程", "教育", "发展", "咨询", "设计", "置业", "投资", "传媒", "服务");

    /**
     * ISBN前缀编码
     */
    private static final String ISBN_PREFIX = "978";

    /**
     * ISBN对应的国家或地区编码
     */
    private static final String ISBN_COUNTRY_OR_REGION_CODE = "7";

    /**
     * 常见的出版社编号范围
     */
    private static final List<Integer[]> publisherCodeList = Lists.newArrayList(
            new Integer[]{5000, 5128},
            new Integer[]{5300, 5480},
            new Integer[]{5600, 5644},
            new Integer[]{80000, 80258},
            new Integer[]{80500, 80756},
            new Integer[]{81002, 81140}
    );

    /**
     * 部门名称
     */
    private static List<String> departmentList = Lists.newArrayList();

    /**
     * 热门手机型号
     */
    private static List<String> mobileModelsList = Lists.newArrayList();

    /**
     * 民族名称
     */
    private static List<String> ethnicNamesList = Lists.newArrayList();

    /**
     * 国民经济行业分类列表
     */
    private static final List<EconomicCategory> economicCategoryList = Lists.newArrayList();

    /**
     * 统一社会信用代码候选字符(不使用I、O、Z、S、V)
     */
    private static final List<String> socialCreditCharactersList = Lists.newArrayList(
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "T", "U", "W", "X", "Y");

    /**
     * 文件后缀
     */
    private static final List<String> fileSuffixList = Lists.newArrayList("txt", "pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "wps", "et", "zip", "rar", "7z", "png", "jpg", "tar");

    private static final OtherSource instance = new OtherSource();

    private OtherSource() {
        departmentList = ResourceUtils.readLines("common-department.txt");
        List<String> economicCategoryLines = ResourceUtils.readLines("national-economic-category.txt");
        if (CollectionUtils.isNotEmpty(economicCategoryLines)) {
            economicCategoryLines.forEach(e -> {
                if (StringUtils.isBlank(e)) {
                    return;
                }
                String[] tmp = e.split(",");
                if (tmp.length == 2) {
                    EconomicCategory ec = new EconomicCategory();
                    ec.setCode(tmp[0]);
                    ec.setName(tmp[1]);
                    economicCategoryList.add(ec);
                }
            });
        }
        mobileModelsList = ResourceUtils.base64DecodeLines(ResourceUtils.readLines("mobile-models.txt"));
        ethnicNamesList = ResourceUtils.base64DecodeLines(ResourceUtils.readLines("ethnic-cn.txt"));
        for (Province province : Province.values()) {
            if (Province.TW.equals(province) || Province.HK.equals(province) || Province.MO.equals(province)) {
                continue;
            }
            provincePrefixList.add(province.getPrefix());
        }
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
        String prefix = ResourceUtils.getRandomElement(provincePrefixList);
        return randomPlateNumber(prefix, isNewEnergyVehicle);
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
     * 生成随机的中国大陆车牌号
     *
     * @param province           省/直辖市/自治区(不含港澳台地区)
     * @param isNewEnergyVehicle 是否为新能源车型
     * @return 随机的中国大陆车牌号
     */
    public String randomPlateNumber(Province province, boolean isNewEnergyVehicle) {
        Preconditions.checkNotNull(province);
        Preconditions.checkArgument(!province.equals(Province.HK) && !province.equals(Province.TW) && !province.equals(Province.MO));
        return randomPlateNumber(province.getPrefix(), isNewEnergyVehicle);
    }

    /**
     * 生成随机的中国大陆车牌号
     *
     * @param provinceNamePrefix 省/直辖市/自治区(不含港澳台地区)简称
     * @param isNewEnergyVehicle 是否为新能源车型
     * @return 随机的中国大陆车牌号
     */
    public String randomPlateNumber(String provinceNamePrefix, boolean isNewEnergyVehicle) {
        int length = 5;
        List<String> plateNumbers = new ArrayList<>(length);
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
        return provinceNamePrefix + ResourceUtils.getRandomElement(plateNumbersList)
                + newEnergyVehicleTag + Joiner.on("").join(plateNumbers);
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
     * 随机公司名称
     *
     * @param province 省级行政区枚举
     * @return 随机公司名称
     */
    public String randomCompanyName(Province province) {
        Preconditions.checkNotNull(province);
        return randomCompanyName(province.getName());
    }

    /**
     * 随机公司部门名称
     *
     * @return 随机公司部门名称
     */
    public String randomCompanyDepartment() {
        return ResourceUtils.getRandomElement(departmentList);
    }

    /**
     * 随机中文句子(兼容)
     *
     * @return 随机中文句子
     */
    public String randomChineseSentence() {
        return LanguageSource.getInstance().randomChineseSentence();
    }

    /**
     * 随机国民经济行业分类信息
     *
     * @return 国民经济行业分类信息
     */
    public EconomicCategory randomEconomicCategory() {
        return ResourceUtils.getRandomElement(economicCategoryList);
    }

    /**
     * 随机民族名称
     *
     * @return 民族名称
     */
    public String randomEthnicName() {
        return ResourceUtils.getRandomElement(ethnicNamesList);
    }

    /**
     * 随机营销号文案(兼容)
     *
     * @param subject  主语
     * @param behavior 行为
     * @return 营销号文案(废话)
     */
    public String randomNonsense(String subject, String behavior) {
        return LanguageSource.getInstance().randomNonsense(subject, behavior);
    }

    /**
     * 随机营销号文案标题(兼容)
     *
     * @param subject  主语
     * @param behavior 行为
     * @return 营销号文案标题
     */
    public String randomNonsenseTitle(String subject, String behavior) {
        return LanguageSource.getInstance().randomNonsenseTitle(subject, behavior);
    }

    /**
     * 随机生成ISBN编号
     *
     * @param withDelimiter 是否包含分隔符-
     * @return ISBN编号
     */
    public String randomISBN(boolean withDelimiter) {
        //随机获得1个出版商编码范围
        Integer[] publisherCode = ResourceUtils.getRandomElement(publisherCodeList);
        //生成指定范围内的随机出版商编码
        String publisher = String.valueOf(RandomUtils.nextInt(publisherCode[0], publisherCode[1] + 1));
        //随机出版物序号(出版商编码+出版物序号总位数为8)
        String seq = RandomStringUtils.randomNumeric(8 - publisher.length());

        List<String> parts = Lists.newArrayList(ISBN_PREFIX, ISBN_COUNTRY_OR_REGION_CODE, publisher, seq);
        //计算校验位
        parts.add(getCheckDigit(Joiner.on("").join(parts)));
        return Joiner.on(withDelimiter ? "-" : "").join(parts);
    }

    /**
     * 随机生成国际商品编码
     *
     * @return 国际商品编码
     */
    public String randomEAN() {
        //前缀：一般为690~692之间的一个数字
        String prefix = String.valueOf(RandomUtils.nextInt(690, 692));

        //随机制造商编码
        String manufacturer = RandomStringUtils.randomNumeric(4);

        //随机商品编码
        String productCode = RandomStringUtils.randomNumeric(5);

        List<String> parts = Lists.newArrayList(prefix, manufacturer, productCode);
        //计算校验位
        parts.add(getCheckDigit(Joiner.on("").join(parts)));
        return Joiner.on("").join(parts);
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

    /**
     * 随机股票(沪A+深A+创业板+科创版, 兼容处理)
     *
     * @return 股票名称+股票代码
     */
    public String[] randomStock() {
        return FinancialSource.getInstance().randomStock();
    }

    /**
     * 随机基金(兼容处理)
     *
     * @return 基金名称+基金代码
     */
    public String[] randomFund() {
        return FinancialSource.getInstance().randomFund();
    }

    /**
     * 随机手机型号
     *
     * @return 随机手机型号
     */
    public String randomMobileModel() {
        return ResourceUtils.getRandomElement(mobileModelsList);
    }

    /**
     * 随机一首唐诗(兼容)
     *
     * @return 唐诗
     */
    public Poem randomTangPoem() {
        return LanguageSource.getInstance().randomTangPoem();
    }

    /**
     * 随机四字成语(兼容)
     *
     * @return 四字成语
     */
    public String randomChineseIdiom() {
        return LanguageSource.getInstance().randomChineseIdiom();
    }

    /**
     * 随机英文文本(兼容)
     *
     * @param words 词语数量
     * @return 随机英文文本
     * @since 1.0.15
     */
    public String randomEnglishText(int words) {
        return LanguageSource.getInstance().randomEnglishText(words);
    }

    /**
     * 随机统一社会信用代码(虚拟)
     *
     * @return 统一社会信用代码(虚拟)
     * @since 1.0.15
     */
    public String randomSocialCreditCode() {
        String prefix = "91";
        //为避免与真实的社会信用代码重合，不计算校验码而是随机生成
        String checkCode = String.valueOf(RandomUtils.nextInt(0, 10));
        Area area = AreaSource.getInstance().nextArea();
        return prefix + area.getZipCode() + Joiner.on("").join(ResourceUtils.getRandomElement(socialCreditCharactersList, 9)) + checkCode;
    }

    /**
     * 随机文件后缀
     *
     * @return 文件后缀(小写)
     */
    public String randomFileSuffix() {
        return ResourceUtils.getRandomElement(fileSuffixList);
    }

    /**
     * 计算校验码
     *
     * @param toCheck 待计算的数字字符串
     * @return 校验码
     */
    private static String getCheckDigit(String toCheck) {
        Preconditions.checkArgument(StringUtils.isNotBlank(toCheck), "待计算的数字字符串为空");
        int total = 0;
        for (int i = 1; i <= toCheck.length(); i++) {
            //偶数位因子：3，奇数位因子：1
            int factor = (i % 2 == 0) ? 3 : 1;
            //依次取出每位数
            int x = Integer.parseInt(String.valueOf(toCheck.charAt(i - 1)));
            //每位数*因子
            int chk = x * factor;
            //求和
            total += chk;
        }
        //总和除以10取余数
        int mod = total % 10;
        //余数=0时校验码为0，余数大于0时校验码为10-余数
        int digit = (mod == 0) ? 0 : 10 - mod;
        return String.valueOf(digit);
    }
}
