package com.apifan.common.random.source;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * 日期时间数据源
 *
 * @author yin
 */
public class DateTimeSource {
    private static final Logger logger = LoggerFactory.getLogger(DateTimeSource.class);

    /**
     * 日期时间格式缓存
     */
    private static Map<String, DateTimeFormatter> dateTimeFormatterMap = new ConcurrentHashMap<>();

    private static final DateTimeSource instance = new DateTimeSource();

    private DateTimeSource() {

    }

    /**
     * 获取唯一实例
     *
     * @return 实例
     */
    public static DateTimeSource getInstance() {
        return instance;
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
        LocalDate date = begin.plusDays(RandomUtils.nextInt(0, isLeapYear ? 366 : 365));
        return date.format(dateTimeFormatterMap.computeIfAbsent(pattern, k -> DateTimeFormatter.ofPattern(pattern)));
    }

    /**
     * 获取特定范围内的随机日期
     *
     * @param beginDate 范围开始(含)
     * @param endDate   范围结束(含)
     * @param pattern   日期格式
     * @return 随机日期字符串
     */
    public String randomDate(LocalDate beginDate, LocalDate endDate, String pattern) {
        Preconditions.checkArgument(beginDate != null && endDate != null, "日期范围不能为空");
        Preconditions.checkArgument(beginDate.isBefore(endDate), "日期范围无效");
        Preconditions.checkArgument(StringUtils.isNotEmpty(pattern), "日期格式为空");
        long diff = DAYS.between(beginDate, endDate);
        LocalDate date = beginDate.plusDays(RandomUtils.nextLong(0, diff + 1));
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
        LocalDate date = baseDate.plusDays(RandomUtils.nextLong(1, 99999));
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
        LocalDate date = baseDate.plusDays(-1 * RandomUtils.nextLong(1, 99999));
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
}
