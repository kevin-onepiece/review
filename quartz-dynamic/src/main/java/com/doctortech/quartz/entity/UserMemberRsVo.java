package com.doctortech.quartz.entity;

import com.doctortech.quartz.enums.MemberOpenModeEnum;
import lombok.Data;

import java.util.Date;

/**
 * @program: doctor-cloud-platform
 * @description: 用户会员状态响应类
 * @author: wwh
 * @create: 2021-09-26 15:42
 */

@Data
public class UserMemberRsVo {
    /**
     * 开通方式
     */
    private MemberOpenModeEnum openMode;

    private Date startTime;

    private Date endTime;

    /**
     * 邀请码来源
     */
    private Long source;

    private String sourceUserName;

    private String sourceUserCompany;

    private String sourceUserMemberName;


    /**
     * 所属团体单位名称
     */
    private String memberName;

    /**
     * 所属团体单位id
     */
    private Long memberId;

    private int money;


}