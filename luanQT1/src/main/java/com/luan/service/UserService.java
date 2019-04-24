package com.luan.service;

import com.luan.model.User;

import java.util.Map;

public interface UserService {
    Map<String,Object> userLogin(User formUser);
    User findUserByusername(String username);

}
