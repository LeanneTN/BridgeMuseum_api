package com.example.bridgemuseum_api.VO;

import lombok.Data;

@Data
public class OrderAddress {
    private String country;
    private String province;
    private String city;
    private String distinct;
    private String preciseAddress;
    private String postCode;
}
