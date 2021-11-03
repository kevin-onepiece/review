package com.doctortech.quartz.entity;

/**
 * @Author: foo
 * @Date: 2021-10-28 14:27
 * @description:
 */


import com.baomidou.mybatisplus.annotation.TableField;
import com.doctortech.quartz.enums.MemberOpenModeEnum;
import com.doctortech.quartz.enums.UserTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Data
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户uuid
     */

    private String uuid;
    /**
     * 主键ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 职务
     */
    private String duty;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 国家id
     */
    private Long countryId;

    /**
     * 省份id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long provinceId;

    /**
     * 城市id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long cityId;


    /**
     * 区县id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long areaId;


    /**
     * 密码
     */
    private String password;
    /**
     * 随机盐
     */
    private String salt;
    /**
     * 微信openid
     */
    private String wxOpenid;
    /**
     * QQ openid
     */
    private String qqOpenid;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 0-正常，1-删除
     */
    private String delFlag;
    /**
     * 锁定标记
     */
    private String lockFlag;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 部门ID
     */

    private String deptId;
    /**
     * 租户ID
     */
    private Long tenantId;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 角色列表
     */
    private List<SysRole> roleList;

    /**
     * 性别
     */
    private String sex;

    /**
     * 用户身份标识
     */
    private String identityId;

    private Integer type;

    @JsonFormat()
    private UserTypeEnum typeName;


    private String companyName;

    private String province;

    private String city;

    private String area;

    /**
     * 技术领域
     */
    @TableField(value = "technology_field")
    private Long technologyField;

    /**
     * 技术领域
     */
    @TableField(value = "technology_field")
    private String technologyFieldValue;


    /**
     * 技术领域2
     */
    @TableField(value = "technology_field1")
    private Long technologyField1;

    /**
     * 技术领域2
     */
    @TableField(value = "technology_field1")
    private String technologyField1Value;

    @TableField(value = "intro")
    private String intro;


    private String roleName;

    private Integer memberFlag;

    private String memberName;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date expireTime;

    private UserMemberRsVo userMemberRsVo;

    /**
     * 主键ID
     */
    private Long memberId;


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


    private int money;


}

