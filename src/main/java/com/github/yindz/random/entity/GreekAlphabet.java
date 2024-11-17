package com.github.yindz.random.entity;

/**
 * 希腊字母
 * 在数学/科学/工程领域中需要用到
 *
 * @author yin
 * @since 1.0.20
 */
public class GreekAlphabet {

    /**
     * 大写形式
     */
    private String upperCase;

    /**
     * 小写形式
     */
    private String lowerCase;

    /**
     * 读音
     */
    private String spell;

    public GreekAlphabet(String upperCase, String lowerCase, String spell) {
        this.upperCase = upperCase;
        this.lowerCase = lowerCase;
        this.spell = spell;
    }

    public String getUpperCase() {
        return upperCase;
    }

    public void setUpperCase(String upperCase) {
        this.upperCase = upperCase;
    }

    public String getLowerCase() {
        return lowerCase;
    }

    public void setLowerCase(String lowerCase) {
        this.lowerCase = lowerCase;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }
}
