package com.example.bridgemuseum_api.controller;

import com.example.bridgemuseum_api.VO.Address;
import com.example.bridgemuseum_api.common.CONSTANT;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.User;
import com.example.bridgemuseum_api.service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/user")
    public CommonResponse<User> login(@RequestParam @NotBlank(message = "username can't be empty") String username,
                                      @RequestParam @NotBlank(message = "password can't be empty") String password,
                                      HttpSession session){
        CommonResponse<User> result = userService.login(username, password);
        if(result.isSuccess()){
            session.setAttribute(CONSTANT.LOGIN_USER, result.getData());
        }
        return result;
    }

    @PostMapping("/user")
    public CommonResponse<Object> register(
            @RequestBody @Valid User user){
        return userService.register(user);
    }

    @GetMapping("/users")
    public CommonResponse<ArrayList<User>> getUserList(@RequestBody @NotBlank User user){
        if (user.getRole() == CONSTANT.ROLE.ADMIN){
            return userService.getUserList(user);
        }
        return CommonResponse.createForError("only admin can watch the user list");
    }

    @GetMapping("/id/{id}")
    public CommonResponse<User> getUserById(@PathVariable("id") Integer id){
        return userService.getDetailById(id);
    }

    @GetMapping("/username/{username}")
    public CommonResponse<User> getUserByUsername(@PathVariable("username") String username){
        return userService.getDetailByName(username);
    }

    @DeleteMapping("/user/{id}")
    public CommonResponse<Object> deleteUserById(@PathVariable("id") Integer id){
        return userService.deleteUserById(id);
    }

    @PostMapping("/id/{id}/password/{oldPassword}/{newPassword}")
    public CommonResponse<Object> modifyPasswordById(@PathVariable("id") @NotBlank Integer id,
                                                     @PathVariable("oldPassword") @NotBlank(message = "oldPassword can't be null") String oldPassword,
                                                     @PathVariable("newPassword") @NotBlank(message = "newPassword can't be null") String newPassword){
        return userService.modifyPasswordById(id, oldPassword, newPassword);
    }

    @PostMapping("/username/{username}/password/{oldPassword}/{newPassword}")
    public CommonResponse<Object> modifyPasswordByUsername(@PathVariable("username") @NotBlank String username,
                                                           @PathVariable("oldPassword") @NotBlank(message = "oldPassword can't be null") String oldPassword,
                                                           @PathVariable("newPassword") @NotBlank(message = "newPassword can't be null") String newPassword){
        return userService.modifyPasswordByUsername(username, oldPassword, newPassword);
    }

    @PostMapping("/username/{oldUsername}/{newUsername}")
    public CommonResponse<User> modifyUsername(@PathVariable("oldUsername") @NotBlank(message = "old username can't be null") String oldUsername,
                                                 @PathVariable("newUsername") @NotBlank(message = "new username can't be null") String newUsername){
        return userService.modifyUsername(oldUsername, newUsername);
    }

    @GetMapping("/{username}/address")
    public CommonResponse<Address> getAddressByUsername(@PathVariable("username") @NotBlank String username){
        return userService.getAddressByUsername(username);
    }

    @PostMapping("/id/{id}/address")
    public CommonResponse<Address> modifyAddress(@PathVariable("id") @NotBlank Integer id,
                                                 @RequestBody @NotBlank Address address){
        return userService.modifyAddressById(id, address);
    }

    @PostMapping("/id/{id}/phone/{phoneNumber}")
    public CommonResponse<Object> modifyPhoneNumber(@PathVariable("id") Integer id,
                                                    @PathVariable("phoneNumber") String phoneNumber){
        return userService.modifyPhoneNumber(id, phoneNumber);
    }

}
