package com.apifan.common.random.source;

import com.apifan.common.random.constant.RandomConstant;
import com.apifan.common.random.util.ResourceUtils;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final String[] ANDROID_MANUFACTURERS = new String[]{"samsung", "sony", "huawei", "hornor", "xiaomi", "redmi", "mi", "vivo", "oppo", "oneplus", "lg", "lenovo", "motorola", "nokia", "meizu", "zte", "asus", "smartisan", "nubia", "realme"};

    /**
     * 安卓 User-Agent模板
     */
    private static final String ANDROID_TEMPLATE = "Mozilla/5.0 (Linux; U; Android 8.0.0; zh-cn; %s-%s Build/%s) AppleWebKit/537.36 (KHTML, like Gecko)Version/4.0 Chrome/57.0.2987.132 MQQBrowser/8.2 Mobile Safari/537.36";

    /**
     * iOS User-Agent模板
     */
    private static final String IOS_TEMPLATE = "Mozilla/5.0 (iPhone; CPU iPhone OS %s like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/%s";

    /**
     * 主流iOS版本号
     */
    private static final String[] IOS_VERSIONS = new String[]{"10_0", "10_1", "10_2", "10_3", "11_0", "11_1", "11_2", "11_3", "11_4", "12_0", "12_1", "12_2", "12_3", "12_4", "13_0", "13_1"};

    /**
     * 主流Windows版本号
     */
    private static final String[] WINDOWS_VERSIONS = new String[]{"6.0", "6.1", "6.2", "6.3", "10.0"};

    /**
     * PC User-Agent模板
     */
    private static final String PC_TEMPLATE = "Mozilla/5.0 (Windows NT %s; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/%d.0.%d.%d Safari/537.36";

    private static final InternetSource instance = new InternetSource();

    private InternetSource() {

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
     * 随机的IPv4地址
     *
     * @return IPv4地址
     */
    public String randomIpv4() {
        return RandomUtils.nextInt(1, 255) + "." + RandomUtils.nextInt(1, 255) + "." + RandomUtils.nextInt(1, 255) + "." + RandomUtils.nextInt(1, 255);
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
        return String.format(ANDROID_TEMPLATE,
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
}
