package com.github.yindz.random.entity;

import java.util.function.Supplier;

/**
 * 数据字段定义
 *
 * @author yin
 * @since 1.0.10
 */
public class DataField {

    /**
     * 字段名
     */
    private String field;

    /**
     * 字段值生成函数
     */
    private Supplier<Object> valueSupplier;

    public DataField(String field, Supplier<Object> valueSupplier) {
        this.field = field;
        this.valueSupplier = valueSupplier;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Supplier<Object> getValueSupplier() {
        return valueSupplier;
    }

    public void setValueSupplier(Supplier<Object> valueSupplier) {
        this.valueSupplier = valueSupplier;
    }
}
