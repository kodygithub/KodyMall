package com.kody.service.impl;

import com.kody.commons.Const;
import com.kody.commons.ServerResponse;
import com.kody.dao.UserMapper;
import com.kody.pojo.User;
import com.kody.service.IUserService;
import com.kody.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Kody on 2017/7/30.
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {

        int result = userMapper.checkUsername(username);
        if (result == 0) {
            return ServerResponse.createByErrorMessage("Invalid username");
        }

        //todo:密码登录
        String MD5Pwd = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username, MD5Pwd);
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功", user);
    }


    @Override
    public ServerResponse<String> register(User user) {


        /*检查username*/
        ServerResponse<String> checkUsername = this.checkValid(user.getUsername(), Const.USERNAME);
        if (!checkUsername.isSuccess()) {
            return checkUsername;
        }

        /*检查email*/
        ServerResponse<String> checkEmail = this.checkValid(user.getEmail(), Const.EMAIL);
        if (!checkEmail.isSuccess()) {
            return checkEmail;
        }

        /*设置用户角色*/
        user.setRole(Const.Role.ROLE_COSTOMER);

        //MD加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));

        /*插入用户注册信息*/
        int result = userMapper.insert(user);
        if (result == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        if (StringUtils.isNotBlank(type)) {
            //开始校验
            if (Const.USERNAME.equals(type)) {
                int i = userMapper.checkUsername(str);
                if (i > 0) {
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            if (Const.EMAIL.equals(type)) {
                int i = userMapper.checkEmail(str);
                if (i > 0) {
                    return ServerResponse.createByErrorMessage("Email已存在");
                }
            }
        } else {
            return ServerResponse.createByErrorMessage("参数错误.");
        }
        return ServerResponse.createBySuccessMessage("检验成功");
    }

}
