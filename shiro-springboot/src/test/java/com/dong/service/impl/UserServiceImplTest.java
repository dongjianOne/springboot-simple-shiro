package com.dong.service.impl;

import com.dong.mapper.UserMapper;
import com.dong.pojo.User;
import com.dong.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author dongjian
 * @Date 2020/2/16 21:48
 * @Description:
 * @Version:1.0
 */
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    void queryUserByName() {
        System.out.println(userService.queryByName("dongjian"));
    }
}