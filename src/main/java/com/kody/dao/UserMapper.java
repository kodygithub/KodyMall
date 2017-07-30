package com.kody.dao;

import com.kody.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String userName);

    User selectLogin(@Param("userName") String userName, @Param("password") String pwd);


    int checkEmail(String email);
}