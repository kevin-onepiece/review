package com.doctortech.quartz.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum SexEnum {

    DEFALUT(-1, "0"),

    SECRECY(0, "保密"),

    MALE(1, "男"),

    FEMALE(2, "女");

    @EnumValue
    private Integer code;

    private String value;

    SexEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
