package com.doctortech.quartz.enums;


import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @Description: 会员开通方式
 * @Author: wwh
 * @date: 2021/9/23
 */
public enum MemberOpenModeEnum {
    PC_OPEN(0, "管理端开通"),

    APPLET_PAY(1, "小程序直接支付"),

    INVITATION_PAY(2, "扫邀请码支付");


    @EnumValue
    private Integer code;

    private String value;

    MemberOpenModeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }


    public static MemberOpenModeEnum getMemberOpenMode(Integer code) {
        MemberOpenModeEnum[] values = MemberOpenModeEnum.values();
        for (MemberOpenModeEnum memberOpenModeEnum : values) {
            if (memberOpenModeEnum.getCode() == code) {
                return memberOpenModeEnum;
            }
        }
        return null;
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
