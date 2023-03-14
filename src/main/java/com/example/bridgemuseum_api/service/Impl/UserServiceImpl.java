package com.example.bridgemuseum_api.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.bridgemuseum_api.common.CONSTANT;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.User;
import com.example.bridgemuseum_api.mapper.UserMapper;
import com.example.bridgemuseum_api.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**
 * @author LeanneTN
 * data: 2023/3/13
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public CommonResponse<User> login(String username, String password) {
        User loginUser = userMapper.selectOne(Wrappers.<User>query().eq("name", username));
        if(loginUser == null){
            return CommonResponse.createForError("username does not exist");
        }
        boolean checkPassword = bCryptPasswordEncoder.matches(password, loginUser.getPassword());
        loginUser.setPassword(StringUtils.EMPTY);
        return checkPassword?CommonResponse.createForSuccess(loginUser):CommonResponse.createForError("wrong username or password");
    }

    @Override
    public CommonResponse<Object> register(User user) {
        String username = user.getName();
        User registerUser = userMapper.selectOne(Wrappers.<User>query().eq("name", username));
        if(registerUser != null){
            return CommonResponse.createForError("username exists");
        }
        User user1 = new User();
        user1.setName(username);
        user1.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user1.setRole(CONSTANT.ROLE.USER);
        user1.setPhone(user.getPhone());
        int rows = userMapper.insert(user1);

        if(rows == 0){
            return CommonResponse.createForError("user register failed");
        }
        return CommonResponse.createForSuccess();
    }
}
