package com.example.bridgemuseum_api.common;

import lombok.Getter;

@Getter
public enum ResponseCode {
    //response success
    SUCCESS(0, "SUCCESS"),
    //response error
    ERROR(1, "ERROR"),
    ARGUMENT_ILLEGAL(2, "ARGUMENT_ILLEGAL"),
    NEED_LOGIN(3, "NEED LOGIN"),
    LIST_EMPTY(4, "LIST IS EMPTY"),

    PASSAGE_OVER_BOUND(21, "PASSAGE IS OVER THE BOUND");

    private final int code;
    private final String description;

    ResponseCode(int code, String description){
        this.code = code;
        this.description = description;
    }
}
