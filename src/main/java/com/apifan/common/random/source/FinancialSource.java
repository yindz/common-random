package com.apifan.common.random.source;

import com.apifan.common.random.entity.CurrencyInfo;
import com.apifan.common.random.entity.KChartData;
import com.apifan.common.random.util.ResourceUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
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
     * 开放式基金
     */
    private static List<String> fundsList = Lists.newArrayList();

    /**
     * 货币信息
     */
    private static List<String> currencyList = Lists.newArrayList();

    private static final FinancialSource instance = new FinancialSource();

    private FinancialSource() {
        stockShSzList = ResourceUtils.base64DecodeLines(ResourceUtils.readLines("stock-shsz.txt"));
        stockHkList = ResourceUtils.base64DecodeLines(ResourceUtils.readLines("stock-hk.txt"));
        stockXsbList = ResourceUtils.base64DecodeLines(ResourceUtils.readLines("stock-xsb.txt"));
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
}
