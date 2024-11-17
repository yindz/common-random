package io.github.yindz.random.source;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 数值数据源
 *
 * @author yin
 */
public class NumberSource {
    private static final Logger logger = LoggerFactory.getLogger(NumberSource.class);

    private static final NumberSource instance = new NumberSource();

    private NumberSource() {

    }

    /**
     * 获取唯一实例
     *
     * @return 实例
     */
    public static NumberSource getInstance() {
        return instance;
    }

    /**
     * 返回1个随机整数
     *
     * @return 1个随机整数
     */
    public int randomInt() {
        return RandomUtils.nextInt();
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
     * @return 1个随机长整数
     */
    public long randomLong() {
        return RandomUtils.nextLong(Long.MIN_VALUE, Long.MAX_VALUE);
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
     * @return 1个随机双精度数
     */
    public double randomDouble() {
        return RandomUtils.nextDouble(Double.MIN_VALUE, Double.MAX_VALUE);
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
     * 随机百分比
     *
     * @return 1个随机百分比
     */
    public BigDecimal randomPercent() {
        return BigDecimal.valueOf(RandomUtils.nextDouble(0.0001d, 0.9999d)).setScale(4, RoundingMode.HALF_UP);
    }
}
