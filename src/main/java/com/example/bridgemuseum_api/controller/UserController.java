package com.example.bridgemuseum_api.controller;

import com.example.bridgemuseum_api.common.CONSTANT;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.User;
import com.example.bridgemuseum_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public CommonResponse<User> login(@RequestParam @NotBlank(message = "username can't be empty") String username,
                                      @RequestParam @NotBlank(message = "password can't be empty") String password,
                                      HttpSession session){
        CommonResponse<User> result = userService.login(username, password);
        if(result.isSuccess()){
            session.setAttribute(CONSTANT.LOGIN_USER, result.getData());
        }
        return result;
    }

    @PostMapping("register")
    public CommonResponse<Object> register(
            @RequestBody @Valid User user){
        return userService.register(user);
    }

}
