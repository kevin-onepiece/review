package com.doctortech.quartz.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: foo
 * @Date: 2021-10-26 14:33
 * @description:
 */
@Mapper
public interface EmployeeMapper {

    Integer count(String name);



    Long selectUserId(String name);

    void insert(String id, Long userId);
}
