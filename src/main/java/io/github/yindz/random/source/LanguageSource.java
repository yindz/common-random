package io.github.yindz.random.source;

import io.github.yindz.random.entity.GreekAlphabet;
import io.github.yindz.random.entity.Poem;
import io.github.yindz.random.util.JsonUtils;
import io.github.yindz.random.util.ResourceUtils;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 语言文字数据源
 *
 * @author yin
 */
public class LanguageSource {
    private static final Logger logger = LoggerFactory.getLogger(LanguageSource.class);

    /**
     * 中文名词
     */
    private static List<String> chineseNounsList = Lists.newArrayList();

    /**
     * 中文动词
     */
    private static List<String> chineseVerbsList = Lists.newArrayList();

    /**
     * 中文副词
     */
    private static List<String> chineseAdverbsList = Lists.newArrayList();

    /**
     * 中文代词
     */
    private static List<String> chinesePronounsList = Lists.newArrayList();

    /**
     * 中文连词
     */
    private static List<String> chineseConjunctionsList = Lists.newArrayList();

    /**
     * 中文助词
     */
    private static List<String> chineseParticlesList = Lists.newArrayList();

    /**
     * 废话模板
     */
    private static List<String> nonsenseList = Lists.newArrayList();

    /**
     * 震惊类前缀
     */
    private static List<String> astonishingPrefixList = Lists.newArrayList();

    /**
     * 标题党模板
     */
    private static List<String> sensationalTitlesList = Lists.newArrayList();

    /**
     * 唐诗
     */
    private static List<Poem> tangPoemsList = Lists.newArrayList();

    /**
     * 四字成语
     */
    private static List<String> chineseIdiomsList = Lists.newArrayList();

    /**
     * 英文常用词语
     */
    private static List<String> englishWordsList = Lists.newArrayList();

    /**
     * 希腊字母
     */
    private static final List<GreekAlphabet> greekAlphabets = Lists.newArrayList();

    private static final LanguageSource instance = new LanguageSource();

    private LanguageSource() {
        chineseNounsList = ResourceUtils.base64DecodeLines(ResourceUtils.readLines("common-chinese-nouns.txt"));
        chinesePronounsList = ResourceUtils.readLines("common-chinese-pronouns.txt");
        chineseAdverbsList = ResourceUtils.readLines("common-chinese-adverbs.txt");
        chineseVerbsList = ResourceUtils.base64DecodeLines(ResourceUtils.readLines("common-chinese-verbs.txt"));
        chineseConjunctionsList = ResourceUtils.readLines("common-chinese-conjunctions.txt");
        chineseParticlesList = ResourceUtils.readLines("common-chinese-particles.txt");
        nonsenseList = ResourceUtils.base64DecodeLines(ResourceUtils.readLines("nonsense.txt"));
        sensationalTitlesList = ResourceUtils.base64DecodeLines(ResourceUtils.readLines("sensational-titles.txt"));
        astonishingPrefixList = ResourceUtils.base64DecodeLines(ResourceUtils.readLines("astonishing-prefix.txt"));
        chineseIdiomsList = ResourceUtils.base64DecodeLines(ResourceUtils.readLines("chinese-idioms.txt"));
        englishWordsList = ResourceUtils.readLines("word-en.txt");
        try {
            tangPoemsList = JsonUtils.parseObjectList(ResourceUtils.readString("tang-poems.json"), Poem.class);
        } catch (Exception e) {
            logger.error("初始化数据异常", e);
        }

        greekAlphabets.add(new GreekAlphabet("Α", "α", "alpha"));
        greekAlphabets.add(new GreekAlphabet("Β", "β", "beta"));
        greekAlphabets.add(new GreekAlphabet("Γ", "γ", "gamma"));
        greekAlphabets.add(new GreekAlphabet("Δ", "δ", "delta"));
        greekAlphabets.add(new GreekAlphabet("Ε", "ε", "epsilon"));
        greekAlphabets.add(new GreekAlphabet("Ζ", "ζ", "zeta"));
        greekAlphabets.add(new GreekAlphabet("Η", "η", "eta"));
        greekAlphabets.add(new GreekAlphabet("Θ", "θ", "theta"));
        greekAlphabets.add(new GreekAlphabet("Ι", "ι", "iota"));
        greekAlphabets.add(new GreekAlphabet("Κ", "κ", "kappa"));
        greekAlphabets.add(new GreekAlphabet("Λ", "λ", "lambda"));
        greekAlphabets.add(new GreekAlphabet("Μ", "μ", "mu"));
        greekAlphabets.add(new GreekAlphabet("Ν", "ν", "nu"));
        greekAlphabets.add(new GreekAlphabet("Ξ", "ξ", "xi"));
        greekAlphabets.add(new GreekAlphabet("Ο", "ο", "omicron"));
        greekAlphabets.add(new GreekAlphabet("Π", "π", "pi"));
        greekAlphabets.add(new GreekAlphabet("Ρ", "ρ", "rho"));
        greekAlphabets.add(new GreekAlphabet("Σ", "σ (ς)", "sigma"));
        greekAlphabets.add(new GreekAlphabet("Τ", "τ", "tau"));
        greekAlphabets.add(new GreekAlphabet("Υ", "υ", "upsilon"));
        greekAlphabets.add(new GreekAlphabet("Φ", "φ", "phi"));
        greekAlphabets.add(new GreekAlphabet("Χ", "χ", "chi"));
        greekAlphabets.add(new GreekAlphabet("Ψ", "ψ", "psi"));
        greekAlphabets.add(new GreekAlphabet("Ω", "ω", "omega"));
    }

    /**
     * 获取唯一实例
     *
     * @return 实例
     */
    public static LanguageSource getInstance() {
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
     * 随机中文句子
     *
     * @return 随机中文句子
     */
    public String randomChineseSentence() {
        StringBuilder sb = new StringBuilder();
        int r = RandomUtils.nextInt(1, 11);
        if (r % 2 == 0) {
            sb.append(ResourceUtils.getRandomElement(chinesePronounsList));
        }
        sb.append(ResourceUtils.getRandomElement(chineseNounsList));
        sb.append(ResourceUtils.getRandomElement(chineseAdverbsList));
        sb.append(ResourceUtils.getRandomElement(chineseVerbsList));
        sb.append(ResourceUtils.getRandomElement(chineseNounsList));
        r = RandomUtils.nextInt(1, 11);
        if (r % 2 == 0) {
            sb.append(ResourceUtils.getRandomElement(chineseNounsList));
        }
        r = RandomUtils.nextInt(1, 101);
        if (r % 2 == 0) {
            r = RandomUtils.nextInt(1, 11);
            if (r % 2 == 0) {
                sb.append("，");
            } else {
                sb.append(ResourceUtils.getRandomElement(chineseParticlesList));
                sb.append("？");
            }
            r = RandomUtils.nextInt(1, 11);
            if (r % 2 == 0) {
                sb.append(ResourceUtils.getRandomElement(chineseConjunctionsList));
            }
            r = RandomUtils.nextInt(1, 11);
            if (r % 3 == 0) {
                sb.append(ResourceUtils.getRandomElement(chinesePronounsList));
            }
            sb.append(ResourceUtils.getRandomElement(chineseNounsList));
            sb.append(ResourceUtils.getRandomElement(chineseVerbsList));
            sb.append(ResourceUtils.getRandomElement(chineseNounsList));
        }
        sb.append("。");
        return sb.toString();
    }

    /**
     * 随机营销号文案
     *
     * @param subject  主语
     * @param behavior 行为
     * @return 营销号文案(废话)
     */
    public String randomNonsense(String subject, String behavior) {
        Preconditions.checkArgument(StringUtils.isNotBlank(subject), "主语不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(behavior), "行为不能为空");
        String tpl = ResourceUtils.getRandomElement(nonsenseList);
        return tpl.replaceAll("A", subject).replaceAll("B", behavior);
    }

    /**
     * 随机营销号文案标题
     *
     * @param subject  主语
     * @param behavior 行为
     * @return 营销号文案标题
     */
    public String randomNonsenseTitle(String subject, String behavior) {
        Preconditions.checkArgument(StringUtils.isNotBlank(subject), "主语不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(behavior), "行为不能为空");
        String tpl = ResourceUtils.getRandomElement(sensationalTitlesList);
        return ResourceUtils.getRandomElement(astonishingPrefixList) + "！" + tpl.replaceAll("A", subject).replaceAll("B", behavior);
    }

    /**
     * 随机一首唐诗
     *
     * @return 唐诗
     */
    public Poem randomTangPoem() {
        return ResourceUtils.getRandomElement(tangPoemsList);
    }

    /**
     * 随机四字成语
     *
     * @return 四字成语
     */
    public String randomChineseIdiom() {
        return ResourceUtils.getRandomElement(chineseIdiomsList);
    }

    /**
     * 随机英文文本
     *
     * @param words 词语数量
     * @return 随机英文文本
     * @since 1.0.15
     */
    public String randomEnglishText(int words) {
        Preconditions.checkArgument(words > 1, "词语数量必须大于1");
        return StringUtils.capitalize(Joiner.on(" ").join(ResourceUtils.getRandomElement(englishWordsList, words)));
    }

    /**
     * 随机希腊字母
     *
     * @return 随机希腊字母
     * @since 1.0.20
     */
    public GreekAlphabet randomGreekAlphabet() {
        return ResourceUtils.getRandomElement(greekAlphabets);
    }
}
