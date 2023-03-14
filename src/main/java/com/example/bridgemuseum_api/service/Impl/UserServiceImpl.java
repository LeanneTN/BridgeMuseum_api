package com.example.bridgemuseum_api.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.bridgemuseum_api.VO.Address;
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
        user1.setProvince(user.getProvince());
        user1.setCity(user.getCity());
        user1.setAddress(user.getAddress());
        int rows = userMapper.insert(user1);

        if(rows == 0){
            return CommonResponse.createForError("user register failed");
        }
        return CommonResponse.createForSuccess();
    }

    @Override
    public CommonResponse<User> getDetailById(int id) {
        User user = userMapper.selectById(id);
        if (user == null){
            return CommonResponse.createForError("user doesn't exist");
        }
        return CommonResponse.createForSuccess(user);
    }

    @Override
    public CommonResponse<User> getDetailByName(String username) {
        User user = userMapper.selectOne(Wrappers.<User>query().eq("name", username));
        if(user == null){
            return CommonResponse.createForError("username doesn't exist");
        }
        return CommonResponse.createForSuccess(user);
    }

    @Override
    public CommonResponse<Object> modifyPasswordById(int id, String oldPassword, String newPassword) {
        User user = userMapper.selectOne(Wrappers.<User>query().eq("id", id));
        return modifyPassword(oldPassword, newPassword, user);
    }

    @Override
    public CommonResponse<Object> modifyPasswordByUsername(String username, String oldPassword, String newPassword) {
        User user = userMapper.selectOne(Wrappers.<User>query().eq("name", username));
        return modifyPassword(oldPassword, newPassword, user);
    }

    private CommonResponse<Object> modifyPassword(String oldPassword, String newPassword, User user) {
        if(user == null){
            return CommonResponse.createForError("user doesn't exist");
        }
        boolean checkPassword = bCryptPasswordEncoder.matches(oldPassword, user.getPassword());
        if(checkPassword){
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        }
        int role = userMapper.updateById(user);
        if (role == 0){
            return CommonResponse.createForError("password modify failed");
        }
        return CommonResponse.createForSuccess();
    }

    @Override
    public CommonResponse<User> modifyUsername(String oldUsername, String newUsername) {
        User user = userMapper.selectOne(Wrappers.<User>query().eq("name", newUsername));
        if(user != null){
            return CommonResponse.createForError("username is occupied");
        }
        User oldUser = userMapper.selectOne(Wrappers.<User>query().eq("name", oldUsername));
        if (oldUser == null){
            return CommonResponse.createForError("user doesn't exist");
        }
        oldUser.setName(newUsername);
        int role = userMapper.updateById(oldUser);
        if(role == 0){
            return CommonResponse.createForError("username modify failed");
        }
        return CommonResponse.createForSuccess(oldUser);
    }

    @Override
    public CommonResponse<Address> getAddressByUsername(String username) {
        User user = userMapper.selectOne(Wrappers.<User>query().eq("name", username));
        if(user == null){
            return CommonResponse.createForError("user doesn't exist");
        }
        Address address = new Address();
        address.setProvince(user.getProvince());
        address.setCity(user.getCity());
        address.setPreciseAddress(user.getAddress());
        return CommonResponse.createForSuccess(address);
    }
}
