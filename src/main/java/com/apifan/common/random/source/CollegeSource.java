package com.apifan.common.random.source;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * 高等教育学校数据源
 *
 * @author yin
 */
public class CollegeSource {
    private static final Logger logger = LoggerFactory.getLogger(CollegeSource.class);

    private List<String> collegeList;

    private static final CollegeSource instance = new CollegeSource();

    private CollegeSource() {
        try {
            collegeList = Resources.asCharSource(Resources.getResource("college.csv"), Charsets.UTF_8).readLines();
        } catch (IOException e) {
            logger.error("初始化数据异常", e);
        }
    }

    /**
     * 获取唯一实例
     *
     * @return 实例
     */
    public static CollegeSource getInstance() {
        return instance;
    }

    /**
     * 生成下一条随机高校名称
     *
     * @return 下一条随机高校名称
     */
    public String next() {
        return collegeList.get(RandomUtils.nextInt(0, collegeList.size()));
    }
}
