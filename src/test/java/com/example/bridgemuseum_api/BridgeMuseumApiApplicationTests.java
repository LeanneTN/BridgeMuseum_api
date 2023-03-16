package com.example.bridgemuseum_api;

import com.example.bridgemuseum_api.domain.Poem;
import com.example.bridgemuseum_api.domain.User;
import com.example.bridgemuseum_api.mapper.PoemMapper;
import com.example.bridgemuseum_api.service.PoemService;
import com.example.bridgemuseum_api.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BridgeMuseumApiApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private PoemMapper poemMapper;

    @Autowired
    private PoemService poemService;
    @Test
    void contextLoads() {

    }

}
