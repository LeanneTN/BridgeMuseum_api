package com.example.bridgemuseum_api.controller;

import com.example.bridgemuseum_api.VO.Article;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.service.PassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passage")
public class PassageController {
    @Autowired
    private PassageService passageService;

    @GetMapping("/id/{headId}")
    public CommonResponse<Article> getArticleByHeadId(@PathVariable("headId") Integer id){
        return passageService.getArticleByPassageHead(id);
    }
}
