package com.example.bridgemuseum_api.service;

import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.User;
import org.springframework.stereotype.Service;

public interface UserService {
    CommonResponse<User> login(String username, String password);

    CommonResponse<Object> register(User user);
}
