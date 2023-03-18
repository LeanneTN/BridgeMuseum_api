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

    public interface COLLECTION_ITEM{
        int BRIDGE = 0;
        int PRODUCT = 1;
        int ARTICLE = 2;
        int POEM = 3;
    }

    public interface CATEGORY{
        int POSTCARD = 0;
        int CLOTHES = 1;
        int SHOES = 2;
        int TROUSERS = 3;
        int GLASSES = 4;
        int BOTTLE = 5;
    }

    public interface PRODUCT_STATUS{
        int AVAILABLE = 0;
        int SOLD_OUT = 1;
    }

    public interface ORDER_STATUS{
        int OPEN = 0;
        int TRANSPORT = 1;
        int CLOSE = 2;
    }

    public interface PAYMENT_TYPE{
        int WECHAT = 0; //DEFAULT
        int ALI_PAY = 1;
        int VISA = 2;
    }
}
