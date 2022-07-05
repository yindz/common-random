package com.apifan.common.random.source;

import com.apifan.common.random.constant.CreditCardType;
import com.apifan.common.random.entity.CurrencyInfo;
import com.apifan.common.random.entity.KChartData;
import com.apifan.common.random.util.ResourceUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 金融相关数据源
 *
 * @author yin
 */
public class FinancialSource {
    private static final Logger logger = LoggerFactory.getLogger(FinancialSource.class);

    private static final DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * 股票(沪A+深A+创业板+科创版)
     */
    private static List<String> stockShSzList = Lists.newArrayList();

    /**
     * 股票(港股)
     */
    private static List<String> stockHkList = Lists.newArrayList();

    /**
     * 股票(新三板)
     */
    private static List<String> stockXsbList = Lists.newArrayList();

    /**
     * 股票(北交所)
     */
    private static List<String> stockBseList = Lists.newArrayList();

    /**
     * 开放式基金
     */
    private static List<String> fundsList = Lists.newArrayList();

    /**
     * 货币信息
     */
    private static List<String> currencyList = Lists.newArrayList();

    private static final String UNION_PAY_PREFIX = "62";

    private static final FinancialSource instance = new FinancialSource();

    private FinancialSource() {
        stockShSzList = ResourceUtils.base64DecodeLines(ResourceUtils.readLines("stock-shsz.txt"));
        stockHkList = ResourceUtils.base64DecodeLines(ResourceUtils.readLines("stock-hk.txt"));
        stockXsbList = ResourceUtils.base64DecodeLines(ResourceUtils.readLines("stock-xsb.txt"));
        stockBseList = ResourceUtils.base64DecodeLines(ResourceUtils.readLines("stock-bse.txt"));
        fundsList = ResourceUtils.readLines("fund.txt");
        currencyList = ResourceUtils.base64DecodeLines(ResourceUtils.readLines("currency.txt"));
    }

    /**
     * 获取唯一实例
     *
     * @return 实例
     */
    public static FinancialSource getInstance() {
        return instance;
    }

    /**
     * 随机股票(沪A+深A+创业板+科创版)
     *
     * @return 股票名称+股票代码
     */
    public String[] randomStock() {
        String stock = ResourceUtils.getRandomString(stockShSzList, 1);
        return StringUtils.isBlank(stock) ? null : stock.split(",");
    }

    /**
     * 随机股票(港股)
     *
     * @return 股票名称+股票代码
     */
    public String[] randomHKStock() {
        String stock = ResourceUtils.getRandomString(stockHkList, 1);
        return StringUtils.isBlank(stock) ? null : stock.split(",");
    }

    /**
     * 随机股票(新三板)
     *
     * @return 股票名称+股票代码
     */
    public String[] randomXsbStock() {
        String stock = ResourceUtils.getRandomString(stockXsbList, 1);
        return StringUtils.isBlank(stock) ? null : stock.split(",");
    }

    /**
     * 随机股票(北交所)
     *
     * @return 股票名称+股票代码
     */
    public String[] randomBseStock() {
        String stock = ResourceUtils.getRandomString(stockBseList, 1);
        return StringUtils.isBlank(stock) ? null : stock.split(",");
    }

    /**
     * 随机基金
     *
     * @return 基金名称+基金代码
     */
    public String[] randomFund() {
        String fund = ResourceUtils.getRandomString(fundsList, 1);
        return StringUtils.isNotEmpty(fund) ? fund.split(",") : null;
    }

    /**
     * 随机日K线数据
     *
     * @param beginPrice 起始价格
     * @param limitUp    单日最大涨幅(0~1之间)
     * @param limitDown  单日最大跌幅(-1~0之间)
     * @param beginDate  起始日期(yyyyMMdd)
     * @param endDate    结束日期(yyyyMMdd)
     * @return 传入的时间范围内的随机日K线数据
     */
    public List<KChartData> randomDailyKChartData(double beginPrice, double limitUp, double limitDown, String beginDate, String endDate) {
        Preconditions.checkArgument(beginPrice > 0, "起始价格必须大于0");
        Preconditions.checkArgument(limitUp > 0 && limitUp < 1, "单日最大涨幅为 0~1 之间的小数");
        Preconditions.checkArgument(limitDown < 0 && limitDown > -1, "单日最大跌幅为 -1~0 之间的小数");

        LocalDate begin;
        try {
            begin = LocalDate.parse(beginDate, yyyyMMdd);
        } catch (Exception e) {
            logger.error("起始日期格式有误", e);
            throw new IllegalArgumentException("起始日期格式有误:" + beginDate);
        }
        LocalDate end;
        try {
            end = LocalDate.parse(endDate, yyyyMMdd);
        } catch (Exception e) {
            logger.error("结束日期格式有误", e);
            throw new IllegalArgumentException("结束日期格式有误:" + endDate);
        }
        Preconditions.checkArgument(end.isAfter(begin), "日期范围有误");

        LocalDate date = begin;
        BigDecimal lastClose = null;
        List<KChartData> kChartDataList = new ArrayList<>();
        while (date.isBefore(end) || date.isEqual(end)) {
            KChartData k = new KChartData();
            k.setDate(yyyyMMdd.format(date));

            //开盘价=前一天的收盘价
            BigDecimal open = lastClose == null ? BigDecimal.valueOf(beginPrice) : lastClose;
            k.setOpen(open);

            //收盘价(随机涨跌)
            int x = NumberSource.getInstance().randomInt(0, 11);
            if (x % 2 == 0) {
                //涨
                k.setClose(open.add(BigDecimal.valueOf(NumberSource.getInstance().randomDouble(0, open.doubleValue() * limitUp))));

                //上涨时，最高价=收盘价，最低价=开盘价
                k.setHigh(k.getClose());
                k.setLow(k.getOpen());
            } else {
                //跌
                k.setClose(open.subtract(BigDecimal.valueOf(NumberSource.getInstance().randomDouble(0, -1 * open.doubleValue() * limitDown))));

                //下跌时，最高价=开盘价，最低价=收盘价
                k.setHigh(k.getOpen());
                k.setLow(k.getClose());
            }
            lastClose = k.getClose();
            kChartDataList.add(k);
            date = date.plusDays(1);
        }
        return kChartDataList;
    }

    /**
     * 随机货币信息
     *
     * @return 随机货币信息
     */
    public CurrencyInfo randomCurrencyInfo() {
        String currency = ResourceUtils.getRandomString(currencyList, 1);
        if (StringUtils.isBlank(currency)) {
            return null;
        }
        String[] tmp = currency.split(",");
        if (tmp.length != 3) {
            return null;
        }
        CurrencyInfo currencyInfo = new CurrencyInfo();
        currencyInfo.setRegion(tmp[0]);
        currencyInfo.setName(tmp[1]);
        currencyInfo.setCode(tmp[2]);
        return currencyInfo;
    }

    /**
     * 随机虚拟信用卡号码
     *
     * <p> 特别说明: 不会与现实中的真实信用卡号产生重合 </p>
     *
     * @param type 信用卡类型
     * @return 随机虚拟信用卡号码
     */
    public String randomCreditCardNo(CreditCardType type) {
        String prefix;
        int length = 12;
        if (CreditCardType.Visa.equals(type)) {
            //VISA信用卡前缀为4，增加99是为了避开现实中的信用卡号码
            prefix = "499";
        } else if (CreditCardType.MasterCard.equals(type)) {
            //MasterCard信用卡前缀为5，增加9是为了避开现实中的信用卡号码
            prefix = "59";
            length = 13;
        } else if (CreditCardType.Amex.equals(type)) {
            //AMEX信用卡前缀为37，增加9是为了避开现实中的信用卡号码
            prefix = "379";
        } else if (CreditCardType.UnionPay.equals(type)) {
            //银联信用卡前缀为62，增加9是为了避开现实中的信用卡号码
            prefix = UNION_PAY_PREFIX + "9";
        } else if (CreditCardType.JCB.equals(type)) {
            //JCB信用卡前缀为35，增加9是为了避开现实中的信用卡号码
            prefix = "359";
        } else {
            prefix = UNION_PAY_PREFIX + "9";
        }
        String digits = prefix + RandomStringUtils.randomNumeric(length);
        return digits + getCheckDigit(digits);
    }

    /**
     * 随机虚拟借记卡号码(银联)
     *
     * <p> 特别说明: 不会与现实中的真实借记卡号产生重合 </p>
     *
     * @return 随机虚拟借记卡号码
     */
    public String randomDebitCardNo() {
        //发卡机构标识码9000~9999为目前尚未实际使用的号段(为了避免不必要的麻烦)
        String digits = UNION_PAY_PREFIX + RandomUtils.nextInt(9000, 10000) + RandomStringUtils.randomNumeric(12);
        return digits + getCheckDigit(digits);
    }

    /**
     * 简单计算校验码(符合Luhn算法)
     *
     * @param toCheck 待计算的数字字符串
     * @return 校验码
     */
    private static String getCheckDigit(String toCheck) {
        Preconditions.checkArgument(StringUtils.isNotBlank(toCheck), "待计算的数字字符串为空");
        //所有待计算的数字
        List<Integer> digits = Lists.newArrayList();
        int length = toCheck.length();
        int maxIndex = length - 1;
        for (int i = maxIndex; i >= 0; i--) {
            int j = maxIndex - i;
            int number = Integer.parseInt(String.valueOf(toCheck.charAt(i)));
            if (j % 2 == 0) {
                int f = number * 2;
                if (f >= 10) {
                    //乘积f大于10时，将十位数与个位数相加重新得到f
                    f = (f / 10) + (f % 10);
                }
                digits.add(f);
            } else {
                digits.add(number);
            }
        }
        //求和
        int total = digits.stream().mapToInt(Integer::intValue).sum();
        //总和除以10取余数
        int mod = total % 10;
        //余数=0时校验码为0，余数大于0时校验码为10-余数
        int digit = (mod == 0) ? 0 : 10 - mod;
        return String.valueOf(digit);
    }
}
