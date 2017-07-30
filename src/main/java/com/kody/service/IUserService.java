package com.kody.service;

import com.kody.commons.ServerResponse;
import com.kody.pojo.User;

/**
 * Created by Kody on 2017/7/30.
 */
public interface IUserService {
    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str, String type);
}
