package com.doctortech.quartz.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.doctortech.quartz.enums.MemberOpenModeEnum;
import lombok.Data;

import java.util.Date;

/**
 * 团队会员与用户关系表
 */
@Data
@TableName(value = "sys_user_member_rs")
public class UserMemberRs {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    /**
     * 开通方式
     */
    @TableField(value = "open_mode")
    private MemberOpenModeEnum openMode;

    @TableField(value = "start_time")
    private Date startTime;

    @TableField(value = "end_time")
    private Date endTime;

    /**
     * 邀请码来源
     */
    @TableField(value = "source")
    private String source;


    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "modify_time", fill = FieldFill.UPDATE)
    private Date modifyTime;

    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private Long createUserId;

    @TableField(value = "deleted")
    private Integer deleted = 0;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "member_id")
    private Long memberId;

    @TableField(value = "money")
    private Integer money;

    @TableField(value = "orderId")
    private String orderId;

}