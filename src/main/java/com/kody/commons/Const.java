package com.kody.commons;

/**
 * Created by Kody on 2017/7/30.
 */
public class Const {
    public static final String CURRENT_USER = "currentUser";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";

    public interface Role {
        int ROLE_COSTOMER = 0;//普通用户
        int ROLE_ADMIN = 1;//系统管理员
    }
}
