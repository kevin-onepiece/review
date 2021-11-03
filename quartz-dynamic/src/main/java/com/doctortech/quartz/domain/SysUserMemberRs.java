package com.doctortech.quartz.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 会员开通表
 * @TableName sys_user_member_rs
 */
@TableName(value ="sys_user_member_rs")
@Data
public class SysUserMemberRs implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date modifyTime;

    /**
     * 
     */
    private Long createUserId;

    /**
     * 
     */
    private Integer deleted;

    /**
     * 
     */
    private Long userId;

    /**
     * 团体会员id，如果没有则代表该用户为个人会员
     */
    private Long memberId;

    /**
     * 开通方式
     */
    private Integer openMode;

    /**
     * 开通时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 邀请码来源，用户id
     */
    private Long source;

    /**
     * 开通会员金额
     */
    private Long money;

    /**
     * 订单号
     */
    private String orderid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}