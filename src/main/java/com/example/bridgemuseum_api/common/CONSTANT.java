package com.example.bridgemuseum_api.common;

public class CONSTANT {
    public static final String LOGIN_USER = "loginUser";
    public interface ROLE{
        int USER = 1;
        int ADMIN = 0;
    }

    public interface BRIDGE_TYPE{
        int HISTORICAL_BRIDGE = 0;
        int RED_BRIDGE_OF_WAR = 1;
    }

    public interface PASSAGE{
        int HEAD = 0;
        int NOT_HEAD = 1;
    }
}
