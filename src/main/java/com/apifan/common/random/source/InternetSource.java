package com.apifan.common.random.source;

import com.apifan.common.random.constant.RandomConstant;
import com.apifan.common.random.entity.IpRange;
import com.apifan.common.random.util.ResourceUtils;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 互联网信息数据源
 *
 * @author yin
 */
public class InternetSource {
    private static final Logger logger = LoggerFactory.getLogger(InternetSource.class);

    /**
     * 主流安卓厂商名称
     */
    private static final String[] ANDROID_MANUFACTURERS = new String[]{"samsung", "sony", "huawei", "hornor", "xiaomi", "redmi", "mi", "vivo", "oppo", "oneplus", "lg", "lenovo", "motorola", "nokia", "meizu", "zte", "asus", "smartisan", "nubia", "realme", "iqoo", "coolpad", "gionee"};

    /**
     * 安卓 User-Agent模板
     */
    private static final String ANDROID_TEMPLATE = "Mozilla/5.0 (Linux; U; Android %d.0.0; zh-cn; %s-%s Build/%s) AppleWebKit/537.36 (KHTML, like Gecko)Version/4.0 Chrome/74.0.3729.157 Mobile Safari/537.36";

    /**
     * iOS User-Agent模板
     */
    private static final String IOS_TEMPLATE = "Mozilla/5.0 (iPhone; CPU iPhone OS %s like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/%s";

    /**
     * 主流iOS版本号
     */
    private static final String[] IOS_VERSIONS = new String[]{"10_0", "10_1", "10_2", "10_3", "11_0", "11_1", "11_2", "11_3", "11_4", "12_0", "12_1", "12_2", "12_3", "12_4", "13_0", "13_1", "13_2", "13_3"};

    /**
     * 主流Windows版本号
     */
    private static final String[] WINDOWS_VERSIONS = new String[]{"6.0", "6.1", "6.2", "6.3", "10.0"};

    /**
     * PC User-Agent模板
     */
    private static final String PC_TEMPLATE = "Mozilla/5.0 (Windows NT %s; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/%d.0.%d.%d Safari/537.36";

    /**
     * IP范围列表
     */
    private List<IpRange> ipRangeList = Lists.newArrayList();

    private static final InternetSource instance = new InternetSource();

    private InternetSource() {
        try {
            List<String> areaLines = ResourceUtils.readLines("cn-ip.csv");
            if (CollectionUtils.isNotEmpty(areaLines)) {
                areaLines.forEach(i -> {
                    if (StringUtils.isEmpty(i)) {
                        return;
                    }
                    List<String> row = Splitter.on(",").splitToList(i);
                    IpRange range = new IpRange();
                    range.setBeginIp(row.get(0));
                    range.setBeginIpNum(ipv4ToLong(range.getBeginIp()));
                    range.setEndIp(row.get(1));
                    range.setEndIpNum(ipv4ToLong(range.getEndIp()));
                    ipRangeList.add(range);
                });
            }
        } catch (Exception e) {
            logger.error("初始化数据异常", e);
        }
    }

    /**
     * 获取唯一实例
     *
     * @return 实例
     */
    public static InternetSource getInstance() {
        return instance;
    }

    /**
     * 生成随机的邮箱地址
     *
     * @param maxLength 邮箱用户名最大长度
     * @return 随机邮箱地址
     */
    public String randomEmail(int maxLength) {
        return randomEmail(maxLength, null);
    }

    /**
     * 生成随机的邮箱地址(指定后缀)
     *
     * @param maxLength 邮箱用户名最大长度
     * @param suffix    后缀
     * @return 随机邮箱地址
     */
    public String randomEmail(int maxLength, String suffix) {
        if (StringUtils.isEmpty(suffix)) {
            suffix = randomDomain(Math.min(3, maxLength));
        }
        //字母开头
        String email = RandomStringUtils.randomAlphabetic(1) +
                RandomStringUtils.randomAlphanumeric(1, Math.min(3, maxLength - 1)) +
                "@" + suffix;
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
                ResourceUtils.getRandomElement(RandomConstant.domainSuffixList);
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
        return "http://" + domain.toLowerCase() + "/" + RandomUtils.nextLong(1L, 10000000000001L) + "/"
                + RandomStringUtils.randomAlphanumeric(8, 33) + "." + suffix;
    }

    /**
     * 随机的公网IPv4地址
     *
     * @return 公网IPv4地址
     */
    public String randomPublicIpv4() {
        IpRange range = ResourceUtils.getRandomElement(ipRangeList);
        if (range == null) {
            return null;
        }
        long ipv4Num = RandomUtils.nextLong(range.getBeginIpNum(), range.getEndIpNum());
        return longToIpv4(ipv4Num);
    }

    /**
     * 随机的私有IPv4地址(内网地址)
     *
     * @return 私有IPv4地址(内网地址)
     */
    public String randomPrivateIpv4() {
        int x = RandomUtils.nextInt(1, 101);
        if (x % 2 == 0) {
            return "10." + RandomUtils.nextInt(0, 256) + "." + RandomUtils.nextInt(0, 256) + "." + RandomUtils.nextInt(0, 256);
        } else if (x % 3 == 0) {
            return "172." + RandomUtils.nextInt(16, 32) + "." + RandomUtils.nextInt(0, 256) + "." + RandomUtils.nextInt(0, 256);
        } else {
            return "192.168." + RandomUtils.nextInt(0, 256) + "." + RandomUtils.nextInt(0, 256);
        }
    }

    /**
     * 随机的PC User-Agent
     *
     * @return PC User-Agent
     */
    public String randomPCUserAgent() {
        return String.format(PC_TEMPLATE,
                WINDOWS_VERSIONS[RandomUtils.nextInt(0, WINDOWS_VERSIONS.length)],
                RandomUtils.nextInt(60, 80),
                RandomUtils.nextInt(2000, 4000),
                RandomUtils.nextInt(1, 200));
    }

    /**
     * 随机的Android User-Agent
     *
     * @return Android User-Agent
     */
    public String randomAndroidUserAgent() {
        int androidVersion = RandomUtils.nextInt(7, 11);
        return String.format(ANDROID_TEMPLATE, androidVersion,
                ANDROID_MANUFACTURERS[RandomUtils.nextInt(0, ANDROID_MANUFACTURERS.length)].toUpperCase(),
                RandomStringUtils.randomAlphanumeric(6).toUpperCase(),
                RandomStringUtils.randomAlphanumeric(6).toUpperCase());
    }

    /**
     * 随机的iOS User-Agent
     *
     * @return iOS User-Agent
     */
    public String randomIOSUserAgent() {
        return String.format(IOS_TEMPLATE,
                IOS_VERSIONS[RandomUtils.nextInt(0, IOS_VERSIONS.length)],
                RandomStringUtils.randomAlphanumeric(6).toUpperCase());
    }

    /**
     * 随机MAC地址
     *
     * @param splitter 分隔符
     * @return 随机MAC地址
     */
    public String randomMacAddress(String splitter) {
        int count = 6;
        List<String> mac = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            int n = RandomUtils.nextInt(0, 255);
            mac.add(String.format("%02x", n));
        }
        return Joiner.on(StringUtils.isNotEmpty(splitter) ? splitter : "-").join(mac).toUpperCase();
    }

    /**
     * 随机端口号
     * 注意: 不会生成1024及以下的端口号
     *
     * @return 随机端口号
     */
    public int randomPort() {
        return RandomUtils.nextInt(1025, 65535);
    }

    /**
     * IPv4地址转整数
     *
     * @param ipv4 IPv4地址
     * @return 整数
     */
    private long ipv4ToLong(String ipv4) {
        String[] part = ipv4.split("\\.");
        long num = 0;
        for (int i = 0; i < part.length; i++) {
            int power = 3 - i;
            num += ((Integer.parseInt(part[i]) % 256 * Math.pow(256, power)));
        }
        return num;
    }

    /**
     * 整数转IPv4地址
     *
     * @param ipv4Num 整数
     * @return IPv4地址
     */
    private String longToIpv4(long ipv4Num) {
        StringBuilder result = new StringBuilder(15);
        for (int i = 0; i < 4; i++) {
            result.insert(0, (ipv4Num & 0xff));
            if (i < 3) {
                result.insert(0, '.');
            }
            ipv4Num = ipv4Num >> 8;
        }
        return result.toString();
    }
}
