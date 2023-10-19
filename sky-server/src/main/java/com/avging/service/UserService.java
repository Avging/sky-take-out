package com.avging.service;

import com.avging.dto.UserLoginDTO;
import com.avging.entity.User;

public interface UserService {

    /**
     * 微信登录
     * @param userLoginDTO UserLoginDTO
     * @return User
     */
    User wxLogin(UserLoginDTO userLoginDTO);
}
