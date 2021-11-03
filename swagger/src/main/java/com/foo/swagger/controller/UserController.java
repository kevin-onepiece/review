package com.foo.swagger.controller;

import com.foo.swagger.POJO.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: foo
 * @Date: 2021-09-30 21:33
 * @description:
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户管理")
public class UserController {

    @GetMapping("/getUser")
    @ApiOperation(value = "获取用户")
    public User getUser() {
        User user = new User(24, "foo");
        return user;
    }


}
