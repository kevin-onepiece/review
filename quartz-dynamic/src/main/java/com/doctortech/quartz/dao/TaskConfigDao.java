package com.doctortech.quartz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.doctortech.quartz.entity.TaskConfigEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskConfigDao extends BaseMapper<TaskConfigEntity> {
}
