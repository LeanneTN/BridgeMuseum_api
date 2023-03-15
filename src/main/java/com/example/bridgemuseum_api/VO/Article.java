package com.example.bridgemuseum_api.VO;

import lombok.Data;

import java.util.List;

@Data
public class Article {
    private List<String> paragraphs;
    private Integer numOfParas;
}
