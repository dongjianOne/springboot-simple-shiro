package com.dong.mapper;

import com.dong.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author dongjian
 * @Date 2020/2/16 21:40
 * @Description:
 * @Version:1.0
 */
@Mapper
public interface UserMapper {
     User queryByName(String name);
}
