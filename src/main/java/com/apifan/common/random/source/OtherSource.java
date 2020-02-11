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

    private static final OtherSource instance = new OtherSource();

    private OtherSource() {

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
        String prefix = provincePrefixList.get(RandomUtils.nextInt(0, provincePrefixList.size()));
        //最多2个字母
        int alphaCnt = RandomUtils.nextInt(0, 3);
        if (alphaCnt > 0) {
            for (int i = 0; i < alphaCnt; i++) {
                plateNumbers.add(plateNumbersList.get(RandomUtils.nextInt(0, plateNumbersList.size())));
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
        return prefix + plateNumbersList.get(RandomUtils.nextInt(0, plateNumbersList.size()))
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
