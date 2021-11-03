

package com.doctortech.quartz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 角色表
 * </p>
 *
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRole extends Model<SysRole> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "role_id", type = IdType.ASSIGN_ID)
	@JsonSerialize(using= ToStringSerializer.class)
	private Long roleId;

	private String roleName;

	private String roleCode;

	private String roleDesc;

	//@NotNull(message = "数据权限类型不能为空")
	private Integer dsType=0;
	/**
	 * 数据权限作用范围
	 */
	private String dsScope;

	/**
	 * 创建时间
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	/**
	 * 修改时间
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;
	/**
	 * 删除标识（0-正常,1-删除）
	 */
	@TableLogic
	private String delFlag;

	private transient String menuIds;
}
