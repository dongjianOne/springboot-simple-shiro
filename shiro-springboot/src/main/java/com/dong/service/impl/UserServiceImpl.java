package com.dong.service.impl;

import com.dong.mapper.UserMapper;
import com.dong.pojo.User;
import com.dong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author dongjian
 * @Date 2020/2/16 21:47
 * @Description:
 * @Version:1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public User queryByName(String name) {
        return userMapper.queryByName(name);
    }
}
