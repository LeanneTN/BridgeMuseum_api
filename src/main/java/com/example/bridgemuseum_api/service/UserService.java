package com.example.bridgemuseum_api.service;

import com.example.bridgemuseum_api.VO.Address;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.User;
import org.springframework.stereotype.Service;

public interface UserService {
    CommonResponse<User> login(String username, String password);

    CommonResponse<Object> register(User user);

    CommonResponse<User> getDetailById(int id);

    CommonResponse<User> getDetailByName(String username);

    CommonResponse<Object> modifyPasswordById(int id, String oldPassword, String newPassword);

    CommonResponse<Object> modifyPasswordByUsername(String username, String oldPassword, String newPassword);

    CommonResponse<User> modifyUsername(String oldUsername, String newUsername);

    CommonResponse<Address> getAddressByUsername(String username);
}
