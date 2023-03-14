package com.example.bridgemuseum_api;

import com.example.bridgemuseum_api.domain.User;
import com.example.bridgemuseum_api.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BridgeMuseumApiApplicationTests {

    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {
        User user = new User();
        user.setName("a");
        user.setPassword("123");
        userService.register(user);
    }

}
