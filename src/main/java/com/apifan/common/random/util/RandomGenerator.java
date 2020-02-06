package com.apifan.common.random.util;

import com.apifan.common.random.constant.RandomConstant;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.io.Resources;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 随机数据生成器
 *
 * @author yin
 */
public class RandomGenerator {
    private static final Logger logger = LoggerFactory.getLogger(RandomGenerator.class);

    /**
     * 日期时间格式缓存
     */
    private static Map<String, DateTimeFormatter> dateTimeFormatterMap = new ConcurrentHashMap<>();

    /**
     * 常见中文姓氏
     */
    private List<String> lastNamesCN = new ArrayList<>();

    /**
     * 常见中文女性名字
     */
    private List<String> femaleFirstNamesCN = new ArrayList<>();

    /**
     * 常见中文男性名字
     */
    private List<String> maleFirstNamesCN = new ArrayList<>();

    /**
     * 常见英文姓氏
     */
    private List<String> lastNamesEN = new ArrayList<>();

    /**
     * 常见英文名字
     */
    private List<String> firstNamesEN = new ArrayList<>();

    /**
     * 中国大陆地址前缀
     */
    private List<String> addressPrefixList = new ArrayList<>();

    /**
     * 中国大陆常见道路名称
     */
    private List<String> roadList = new ArrayList<>();

    /**
     * 道路名称中常见的方向
     */
    private List<String> directionList = new ArrayList<>();

    private static final RandomGenerator generator = new RandomGenerator();

    private RandomGenerator() {
        try {
            lastNamesCN = Resources.asCharSource(Resources.getResource("last-names-cn.txt"), Charsets.UTF_8).readLines();
            femaleFirstNamesCN = Resources.asCharSource(Resources.getResource("female-first-names-cn.txt"), Charsets.UTF_8).readLines();
            maleFirstNamesCN = Resources.asCharSource(Resources.getResource("male-first-names-cn.txt"), Charsets.UTF_8).readLines();
            lastNamesEN = Resources.asCharSource(Resources.getResource("last-names-en.txt"), Charsets.UTF_8).readLines();
            firstNamesEN = Resources.asCharSource(Resources.getResource("first-names-en.txt"), Charsets.UTF_8).readLines();
            addressPrefixList = Resources.asCharSource(Resources.getResource("address-prefix-cn.txt"), Charsets.UTF_8).readLines();
            roadList = Resources.asCharSource(Resources.getResource("address-road-cn.txt"), Charsets.UTF_8).readLines();
            directionList = Resources.asCharSource(Resources.getResource("address-direction-cn.txt"), Charsets.UTF_8).readLines();
        } catch (IOException e) {
            logger.error("初始化数据异常", e);
        }
    }

    public static RandomGenerator getInstance() {
        return generator;
    }

    /**
     * 返回1个随机整数
     *
     * @param startInclusive 开始(含)
     * @param endExclusive   结束(不含)
     * @return 1个随机整数
     */
    public int randomInt(final int startInclusive, final int endExclusive) {
        return RandomUtils.nextInt(startInclusive, endExclusive);
    }

    /**
     * 返回N个随机整数
     *
     * @param startInclusive 开始(含)
     * @param endExclusive   结束(不含)
     * @param count          随机整数个数
     * @return N个随机整数
     */
    public int[] randomInt(final int startInclusive, final int endExclusive, final int count) {
        Preconditions.checkArgument(count > 0, "随机整数个数必须大于0");
        int[] nums = new int[count];
        for (int i = 0; i < count; i++) {
            nums[i] = randomInt(startInclusive, endExclusive);
        }
        return nums;
    }

    /**
     * 返回1个随机长整数
     *
     * @param startInclusive 开始(含)
     * @param endExclusive   结束(不含)
     * @return 1个随机长整数
     */
    public long randomLong(final long startInclusive, final long endExclusive) {
        return RandomUtils.nextLong(startInclusive, endExclusive);
    }

    /**
     * 返回N个随机长整数
     *
     * @param startInclusive 开始(含)
     * @param endExclusive   结束(不含)
     * @param count          随机长整数个数
     * @return N个随机长整数
     */
    public long[] randomLong(final long startInclusive, final long endExclusive, final int count) {
        Preconditions.checkArgument(count > 0, "随机长整数个数必须大于0");
        long[] nums = new long[count];
        for (int i = 0; i < count; i++) {
            nums[i] = randomLong(startInclusive, endExclusive);
        }
        return nums;
    }

    /**
     * 返回1个随机双精度数
     *
     * @param startInclusive 开始(含)
     * @param endExclusive   结束(不含)
     * @return 1个随机双精度数
     */
    public double randomDouble(final double startInclusive, final double endExclusive) {
        return RandomUtils.nextDouble(startInclusive, endExclusive);
    }

    /**
     * 返回N个随机双精度数
     *
     * @param startInclusive 开始(含)
     * @param endExclusive   结束(不含)
     * @param count          随机双精度数个数
     * @return N个随机双精度数
     */
    public double[] randomDouble(final double startInclusive, final double endExclusive, final int count) {
        Preconditions.checkArgument(count > 0, "随机双精度数个数必须大于0");
        double[] nums = new double[count];
        for (int i = 0; i < count; i++) {
            nums[i] = randomDouble(startInclusive, endExclusive);
        }
        return nums;
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
     * 生成随机的中文人名
     *
     * @return 随机中文人名
     */
    public String randomChineseName() {
        //随机取一个常见姓氏
        StringBuilder name = new StringBuilder(lastNamesCN.get(randomInt(0, lastNamesCN.size())));
        //名字1~2个字（随机）
        int length = randomInt(1, 3);
        boolean isFemale = randomInt(1, 99999) % 2 == 0;
        for (int i = 0; i < length; i++) {
            if (isFemale) {
                name.append(femaleFirstNamesCN.get(randomInt(0, femaleFirstNamesCN.size())));
            } else {
                name.append(maleFirstNamesCN.get(randomInt(0, maleFirstNamesCN.size())));
            }

        }
        return name.toString();
    }

    /**
     * 生成随机的英文人名
     *
     * @return 随机英文人名
     */
    public String randomEnglishName() {
        return firstNamesEN.get(randomInt(0, firstNamesEN.size())) + " " + lastNamesEN.get(randomInt(0, lastNamesEN.size()));
    }

    /**
     * 生成随机的中国手机号
     *
     * @return 随机中国手机号
     */
    public String randomChineseMobile() {
        StringBuilder mobile = new StringBuilder(RandomConstant.mobilePrefixList.get(randomInt(0, RandomConstant.mobilePrefixList.size())));
        int x = mobile.length() == 3 ? 8 : 11 - mobile.length();
        for (int i = 0; i < x; i++) {
            mobile.append(randomInt(0, 10));
        }
        return mobile.toString();
    }

    /**
     * 生成随机的邮箱地址
     *
     * @param maxLength 邮箱用户名最大长度
     * @return 随机邮箱地址
     */
    public String randomEmail(int maxLength) {
        //字母开头
        String email = RandomStringUtils.randomAlphabetic(1) +
                RandomStringUtils.randomAlphanumeric(1, Math.min(3, maxLength - 1)) +
                "@" + randomDomain(Math.min(3, maxLength));
        return email.toLowerCase();
    }

    /**
     * 生成随机的域名
     *
     * @param maxLength 域名最大长度
     * @return 随机域名
     */
    public String randomDomain(int maxLength) {
        String domain = RandomStringUtils.randomAlphanumeric(1, Math.min(3, maxLength)) +
                RandomStringUtils.randomAlphanumeric(2, 17) + "." +
                RandomConstant.domainSuffixList.get(randomInt(0, RandomConstant.domainSuffixList.size()));
        return domain.toLowerCase();
    }

    /**
     * 生成随机的静态URL
     *
     * @param suffix 后缀
     * @return 随机的静态URL
     */
    public String randomStaticUrl(String suffix) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(suffix), "后缀为空");
        String domain = randomDomain(16);
        return "http://" + domain.toLowerCase() + "/" + randomLong(1L, 10000000000001L) + "/"
                + RandomStringUtils.randomAlphanumeric(8, 33) + "." + suffix;
    }

    /**
     * 随机日期
     *
     * @param year    年份
     * @param pattern 日期格式
     * @return 随机日期字符串
     */
    public String randomDate(int year, String pattern) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(pattern), "日期格式为空");
        Preconditions.checkArgument(year >= 1970 && year <= 9999, "年份无效");
        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
        LocalDate begin = LocalDate.of(year, 1, 1);
        LocalDate date = begin.plusDays(randomLong(0, isLeapYear ? 366 : 365));
        return date.format(dateTimeFormatterMap.computeIfAbsent(pattern, k -> DateTimeFormatter.ofPattern(pattern)));
    }

    /**
     * 随机未来日期
     *
     * @param baseDate 基础日期
     * @param pattern  日期格式
     * @return 随机未来日期字符串
     */
    public String randomFutureDate(LocalDate baseDate, String pattern) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(pattern), "日期格式为空");
        LocalDate date = baseDate.plusDays(randomLong(1, 99999));
        return date.format(dateTimeFormatterMap.computeIfAbsent(pattern, k -> DateTimeFormatter.ofPattern(pattern)));
    }

    /**
     * 随机未来日期(以当天为基准)
     *
     * @param pattern 日期格式
     * @return 随机未来日期字符串
     */
    public String randomFutureDate(String pattern) {
        return randomFutureDate(LocalDate.now(), pattern);
    }

    /**
     * 随机以往日期
     *
     * @param baseDate 基础日期
     * @param pattern  日期格式
     * @return 随机以往日期字符串
     */
    public String randomPastDate(LocalDate baseDate, String pattern) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(pattern), "日期格式为空");
        LocalDate date = baseDate.plusDays(-1 * randomLong(1, 99999));
        return date.format(dateTimeFormatterMap.computeIfAbsent(pattern, k -> DateTimeFormatter.ofPattern(pattern)));
    }

    /**
     * 随机以往日期
     *
     * @param pattern 日期格式
     * @return 随机以往日期字符串
     */
    public String randomPastDate(String pattern) {
        return randomPastDate(LocalDate.now(), pattern);
    }

    /**
     * 生成随机强密码
     *
     * @param length         长度
     * @param useSpecialChar 是否使用特殊字符
     * @return 随机强密码
     */
    public String randomStrongPassword(int length, boolean useSpecialChar) {
        if (length < 8) {
            //至少8位
            length = 8;
        }
        List<String> pwd = new ArrayList<>();
        //最多1/3的大写字母和小写字母
        int oneThirdCount = length / 3;
        for (int i = 0; i < oneThirdCount; i++) {
            pwd.add(RandomStringUtils.randomAlphabetic(1).toUpperCase());
            pwd.add(RandomStringUtils.randomAlphabetic(1).toLowerCase());
        }

        //特殊字符
        if (useSpecialChar) {
            int b = randomInt(1, oneThirdCount);
            if (b > 0) {
                for (int i = 0; i < b; i++) {
                    pwd.add(RandomConstant.specialCharList.get(randomInt(0, RandomConstant.specialCharList.size())));
                }
            }
        }

        //剩下不足的用数字填充
        if (pwd.size() < length) {
            int digitCount = length - pwd.size();
            for (int i = 0; i < digitCount; i++) {
                pwd.add(RandomStringUtils.randomNumeric(1));
            }
        }

        //打乱顺序
        Collections.shuffle(pwd);
        return Joiner.on("").join(pwd);
    }

    /**
     * 生成随机的中国大陆地址
     *
     * @return 随机的中国大陆地址
     */
    public String randomAddress() {
        String prefix = addressPrefixList.get(RandomUtils.nextInt(0, addressPrefixList.size()));
        String road = roadList.get(RandomUtils.nextInt(0, roadList.size())) + directionList.get(RandomUtils.nextInt(0, directionList.size()));
        return prefix + road + "路" + randomInt(1, 1000) + "号";
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
        String prefix = RandomConstant.provincePrefixList.get(RandomUtils.nextInt(0, RandomConstant.provincePrefixList.size()));
        //最多2个字母
        int alphaCnt = randomInt(0, 3);
        if (alphaCnt > 0) {
            for (int i = 0; i < alphaCnt; i++) {
                plateNumbers.add(RandomConstant.plateNumbersList.get(RandomUtils.nextInt(0, RandomConstant.plateNumbersList.size())));
            }
        }
        //剩余部分全是数字
        int numericCnt = length - alphaCnt;
        for (int i = 0; i < numericCnt; i++) {
            plateNumbers.add(String.valueOf(randomInt(0, 10)));
        }
        //打乱顺序
        Collections.shuffle(plateNumbers);

        String newEnergyVehicleTag = "";
        if (isNewEnergyVehicle) {
            int j = randomInt(0, 2);
            //新能源车牌前缀为D或F
            newEnergyVehicleTag = (j == 0 ? "D" : "F");
        }
        return prefix + RandomConstant.plateNumbersList.get(RandomUtils.nextInt(0, RandomConstant.plateNumbersList.size()))
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
}
