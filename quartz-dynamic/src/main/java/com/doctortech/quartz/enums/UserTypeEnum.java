package com.doctortech.quartz.enums;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @program: doctor-cloud-platform
 * @description: 用户类型枚举
 * @author: wwh
 * @create: 2021-09-22 16:31
 */

public enum UserTypeEnum {


    college_research_institution(1, "高校及科研院所"),

    ENTERPRISE(2, "企业"),

    government(3, "政府单位"),

    SERVICE_INSTITUTION(4, "服务机构"),

    industry_association(5, "行业协会"),

    other(6, "其他");


    @EnumValue
    private Integer code;

    private String value;

    UserTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public final Integer getCode() {
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

    public static UserTypeEnum getUserTypeEnum(Integer code) {
        UserTypeEnum[] values = UserTypeEnum.values();
        for (UserTypeEnum userTypeEnum : values) {
            if (userTypeEnum.getCode() == code) {
                return userTypeEnum;
            }
        }
        return null;
    }

    public static Integer getCode(String value) {
        UserTypeEnum[] values = UserTypeEnum.values();
        for (UserTypeEnum userTypeEnum : values) {
            if (StringUtils.equalsIgnoreCase(userTypeEnum.getValue(), value)) {
                return userTypeEnum.getCode();
            }
        }
        return null;
    }
}