package com.apifan.common.random.source;

import com.apifan.common.random.constant.RandomConstant;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
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
 * 互联网信息数据源
 *
 * @author yin
 */
public class InternetSource {
    private static final Logger logger = LoggerFactory.getLogger(InternetSource.class);

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
                RandomConstant.domainSuffixList.get(RandomUtils.nextInt(0, RandomConstant.domainSuffixList.size()));
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
}
