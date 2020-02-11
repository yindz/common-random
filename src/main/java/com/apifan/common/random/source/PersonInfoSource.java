package com.apifan.common.random.source;

import com.apifan.common.random.constant.RandomConstant;
import com.apifan.common.random.util.ResourceUtils;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 个人信息数据源
 *
 * @author yin
 */
public class PersonInfoSource {
    private static final Logger logger = LoggerFactory.getLogger(PersonInfoSource.class);

    /**
     * 默认字体
     */
    private static final Font defaultFont = new Font("Dialog", Font.PLAIN, 78);

    /**
     * 姓名图片可选颜色
     */
    private static final List<String> namePictureColorsList = Lists.newArrayList(
            "255,111,97", "107,91,149", "136,176,75", "146,168,209", "149,82,81"
            , "181,101,167", "0,155,119", "221,65,36", "214,80,118", "68,184,172"
            , "239,192,80", "91,94,166", "155,35,53", "223,207,190", "85,180,176"
            , "225,93,68", "127,205,205", "188,36,60", "195,68,122", "152,180,212");

    /**
     * 字体对象缓存
     */
    private static Map<String, Font> fontMap = new ConcurrentHashMap<>();

    /**
     * 常见中文姓氏
     */
    private List<String> lastNamesCN;

    /**
     * 常见中文女性名字
     */
    private List<String> femaleFirstNamesCN;

    /**
     * 常见中文男性名字
     */
    private List<String> maleFirstNamesCN;

    /**
     * 常见英文姓氏
     */
    private List<String> lastNamesEN;

    /**
     * 常见英文名字
     */
    private List<String> firstNamesEN;

    private static final PersonInfoSource instance = new PersonInfoSource();

    private PersonInfoSource() {
        lastNamesCN = ResourceUtils.readLines("last-names-cn.txt");
        femaleFirstNamesCN = ResourceUtils.readLines("female-first-names-cn.txt");
        maleFirstNamesCN = ResourceUtils.readLines("male-first-names-cn.txt");
        lastNamesEN = ResourceUtils.readLines("last-names-en.txt");
        firstNamesEN = ResourceUtils.readLines("first-names-en.txt");
    }

    /**
     * 获取唯一实例
     *
     * @return 实例
     */
    public static PersonInfoSource getInstance() {
        return instance;
    }

    /**
     * 生成随机的中文人名
     *
     * @return 随机中文人名
     */
    public String randomChineseName() {
        //随机取一个常见姓氏
        StringBuilder name = new StringBuilder(lastNamesCN.get(RandomUtils.nextInt(0, lastNamesCN.size())));
        //名字1~2个字（随机）
        int length = RandomUtils.nextInt(1, 3);
        boolean isFemale = RandomUtils.nextInt(1, 99999) % 2 == 0;
        for (int i = 0; i < length; i++) {
            if (isFemale) {
                name.append(femaleFirstNamesCN.get(RandomUtils.nextInt(0, femaleFirstNamesCN.size())));
            } else {
                name.append(maleFirstNamesCN.get(RandomUtils.nextInt(0, maleFirstNamesCN.size())));
            }

        }
        return name.toString();
    }

    /**
     * 生成随机的英文人名
     *
     * @return 随机英文人名
     */
    public String randomEnglishName() {
        return firstNamesEN.get(RandomUtils.nextInt(0, firstNamesEN.size())) + " " + lastNamesEN.get(RandomUtils.nextInt(0, lastNamesEN.size()));
    }

    /**
     * 随机昵称
     *
     * @param maxLength 最大长度
     * @return 随机的昵称
     */
    public String randomNickName(int maxLength) {
        Preconditions.checkArgument(maxLength > 5, "长度必须大于5");
        //必须以字母开头
        StringBuilder sb = new StringBuilder(RandomStringUtils.randomAlphabetic(1));
        int actualLength = RandomUtils.nextInt(4, maxLength + 1);
        sb.append(RandomStringUtils.randomAlphanumeric(actualLength - 1));
        return sb.toString();
    }

    /**
     * 生成随机的中国手机号
     *
     * @return 随机中国手机号
     */
    public String randomChineseMobile() {
        StringBuilder mobile = new StringBuilder(RandomConstant.mobilePrefixList.get(RandomUtils.nextInt(0, RandomConstant.mobilePrefixList.size())));
        int x = mobile.length() == 3 ? 8 : 11 - mobile.length();
        for (int i = 0; i < x; i++) {
            mobile.append(RandomUtils.nextInt(0, 10));
        }
        return mobile.toString();
    }

    /**
     * 生成随机强密码
     *
     * @param length         长度
     * @param useSpecialChar 是否使用特殊字符
     * @return 随机强密码
     */
    public String randomStrongPassword(int length, boolean useSpecialChar) {
        if (length < 8) {
            //至少8位
            length = 8;
        }
        List<String> pwd = new ArrayList<>();
        //最多1/3的大写字母和小写字母
        int oneThirdCount = length / 3;
        for (int i = 0; i < oneThirdCount; i++) {
            pwd.add(RandomStringUtils.randomAlphabetic(1).toUpperCase());
            pwd.add(RandomStringUtils.randomAlphabetic(1).toLowerCase());
        }

        //特殊字符
        if (useSpecialChar) {
            int b = RandomUtils.nextInt(1, oneThirdCount);
            if (b > 0) {
                for (int i = 0; i < b; i++) {
                    pwd.add(RandomConstant.specialCharList.get(RandomUtils.nextInt(0, RandomConstant.specialCharList.size())));
                }
            }
        }

        //剩下不足的用数字填充
        if (pwd.size() < length) {
            int digitCount = length - pwd.size();
            for (int i = 0; i < digitCount; i++) {
                pwd.add(RandomStringUtils.randomNumeric(1));
            }
        }

        //打乱顺序
        Collections.shuffle(pwd);
        return Joiner.on("").join(pwd);
    }

    /**
     * 生成随机的QQ号码
     *
     * @return 随机的QQ号码
     */
    public String randomQQAccount() {
        //目前QQ号码最短5位，最长11位
        return String.valueOf(RandomUtils.nextLong(10000L, 100000000000L));
    }

    /**
     * 生成姓名图片文件
     *
     * @param name     姓名
     * @param savePath 图片文件的保存路径
     * @param fontPath 第三方字体的路径
     * @throws IOException IO异常
     */
    public void generateNamePicture(String name, String savePath, String fontPath) throws IOException {
        Preconditions.checkArgument(StringUtils.isNotEmpty(name), "姓名不能为空");
        Preconditions.checkArgument(StringUtils.isNotEmpty(savePath), "图片保存路径不能为空");
        String text = name;
        if (name.length() > 1) {
            //长度超过1时，随机取一个字
            int i = RandomUtils.nextInt(0, name.length());
            text = name.substring(i, i + 1);
        }
        File file = new File(savePath);
        if (file.exists()) {
            throw new IllegalArgumentException("文件 " + savePath + " 已存在");
        }
        int width = 200, height = 200;
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setBackground(getRandomColor());
        g2.clearRect(0, 0, width, height);
        //文字为白色
        g2.setPaint(Color.WHITE);
        g2.setFont(getFont(fontPath));
        g2.drawString(text, 62, 126);
        ImageIO.write(bi, "png", file);
        logger.info("{} 的姓名图片已生成到 {}", name, savePath);
    }

    /**
     * 生成姓名图片文件(使用默认字体)
     *
     * @param name     姓名
     * @param savePath 图片文件的保存路径
     * @throws IOException IO异常
     */
    public void generateNamePicture(String name, String savePath) throws IOException {
        generateNamePicture(name, savePath, null);
    }

    /**
     * 获取字体
     *
     * @param fontPath 字体文件路径
     * @return 字体
     */
    private Font getFont(String fontPath) {
        if (StringUtils.isEmpty(fontPath)) {
            logger.warn("字体文件路径为空! 将使用默认字体 {}", defaultFont.getName());
            return defaultFont;
        }
        File fontFile = new File(fontPath);
        if (!fontFile.exists()) {
            logger.warn("字体文件 {} 不存在! 将使用默认字体 {}", fontPath, defaultFont.getName());
            return defaultFont;
        }
        return fontMap.computeIfAbsent(fontPath, k -> {
            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Float.parseFloat("78"));
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(font);
                return font;
            } catch (FontFormatException | IOException e) {
                logger.error("初始化字体异常", e);
                return null;
            }
        });
    }

    /**
     * 获取随机颜色
     *
     * @return 随机颜色
     */
    private Color getRandomColor() {
        String[] color = namePictureColorsList.get(RandomUtils.nextInt(0, namePictureColorsList.size())).split(",");
        return new Color(Integer.parseInt(color[0]), Integer.parseInt(color[1]), Integer.parseInt(color[2]));
    }
}
