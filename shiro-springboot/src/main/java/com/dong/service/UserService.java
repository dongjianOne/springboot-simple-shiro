package com.dong.service;

import com.dong.pojo.User;
/**
 * @Author dongjian
 * @Date 2020/2/16 21:46
 * @Description:
 * @Version:1.0
 */
public interface UserService {
    User queryByName(String name);
}
