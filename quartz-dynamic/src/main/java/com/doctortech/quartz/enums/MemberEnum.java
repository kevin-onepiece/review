package com.doctortech.quartz.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum MemberEnum {

    NOT_MEMBER( 0, "非会员"),
    IS_MEMBER(1, "会员");

    @EnumValue
    private Integer code;

    private String value;

    MemberEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }

}
