package com.example.bridgemuseum_api.VO;

import lombok.Data;

import java.util.List;

@Data
public class Picture {
    // id of the bridge which owns these pictures
    private Long idOfBridge;
    // urls of picture
    private List<String> urlOfPics;
    private Integer numOfPics;
}
