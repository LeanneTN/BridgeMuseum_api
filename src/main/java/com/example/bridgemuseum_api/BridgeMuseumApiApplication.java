package com.example.bridgemuseum_api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.bridgemuseum_api.mapper")
public class BridgeMuseumApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BridgeMuseumApiApplication.class, args);
    }

}
