package com.apifan.common.random.source;

import com.apifan.common.random.entity.Area;
import com.apifan.common.random.util.ResourceUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 教育信息数据源
 *
 * @author yin
 */
public class EducationSource {
    private static final Logger logger = LoggerFactory.getLogger(EducationSource.class);

    /**
     * 教育程度
     */
    private static final List<String> degreeList = Lists.newArrayList("小学", "初中", "中专/职业高中", "高中", "大学专科", "大学本科", "硕士", "博士");
    private static final EducationSource instance = new EducationSource();
    private List<String> collegeList;
    private static final List<String> numberList = Lists.newArrayList("一", "二", "三", "四", "五", "六", "七", "八", "九", "十");

    private List<String> majorList;

    private EducationSource() {
        collegeList = ResourceUtils.readLines("college.txt");
        majorList = ResourceUtils.base64DecodeLines(ResourceUtils.readLines("college-major.txt"));
    }

    /**
     * 获取唯一实例
     *
     * @return 实例
     */
    public static EducationSource getInstance() {
        return instance;
    }

    /**
     * 获取下一条随机高校名称
     *
     * @return 下一条随机高校名称
     */
    public String randomCollege() {
        return ResourceUtils.getRandomElement(collegeList);
    }

    /**
     * 获取随机学历
     *
     * @return 随机学历
     */
    public String randomDegree() {
        return ResourceUtils.getRandomElement(degreeList);
    }

    /**
     * 获取随机小学名称
     *
     * @return 随机小学名称
     */
    public String randomPrimarySchoolName() {
        return randomSchoolName("小学");
    }

    /**
     * 获取随机小学年级
     *
     * @return 随机小学年级
     */
    public String randomPrimarySchoolGrade() {
        return getGradeName(RandomUtils.nextInt(1, 7)) + "年级";
    }

    /**
     * 获取随机中学名称
     *
     * @return 随机中学名称
     */
    public String randomHighSchoolName() {
        return randomSchoolName("中学");
    }

    /**
     * 获取随机中学年级
     *
     * @return 随机中学年级
     */
    public String randomHighSchoolGrade() {
        String prefix;
        int grade = RandomUtils.nextInt(1, 7);
        if (grade > 3) {
            prefix = "高";
            grade -= 3;
        } else {
            prefix = "初";
        }
        return prefix + getGradeName(grade) + "年级";
    }

    /**
     * 获取随机班级名称
     *
     * @return 随机班级名称
     */
    public String randomClassName() {
        return RandomUtils.nextInt(1, 11) + "班";
    }

    /**
     * 获取随机大学专业名称
     *
     * @return 大学专业名称
     */
    public String randomMajorName() {
        return ResourceUtils.getRandomElement(majorList);
    }

    private String getGradeName(int grade) {
        Preconditions.checkArgument(grade > 0 && grade < numberList.size(), "grade数字错误");
        return numberList.get(grade - 1);
    }

    private String randomSchoolName(String suffix) {
        Area area = AreaSource.getInstance().nextArea();
        return area.getCity() + "第" + ResourceUtils.getRandomString(numberList, 1) + suffix;
    }
}
