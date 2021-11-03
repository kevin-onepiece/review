package com.doctortech.quartz.service;

import com.doctortech.quartz.mapper.EmployeeMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: foo
 * @Date: 2021-10-26 14:33
 * @description:
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public int count(String name) {
        return employeeMapper.count(name);
    }




    public Long selectUserId(String name) {
        return employeeMapper.selectUserId(name);
    }

    public void insert(String id, long userId) {
        employeeMapper.insert(id, userId);
    }
}
