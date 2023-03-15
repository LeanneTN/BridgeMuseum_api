package com.example.bridgemuseum_api.controller;

import com.example.bridgemuseum_api.VO.Article;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.Passage;
import com.example.bridgemuseum_api.service.PassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/passage")
public class PassageController {
    @Autowired
    private PassageService passageService;

    @GetMapping("/id/{headId}")
    public CommonResponse<Article> getArticleByHeadId(@PathVariable("headId") Integer id) {
        return passageService.getArticleByPassageHead(id);
    }

    @PutMapping("/userId/{userId}")
    public CommonResponse<Article> addArticle(@PathVariable("userId") Long userId,
                                              @RequestBody @NotBlank Article article){
        return passageService.addArticle(article, userId);
    }

    @PostMapping("/userId/{userId}/headId/{headId}")
    public CommonResponse<Article> modifyArticle(@PathVariable("userId") Long userId,
                                                 @PathVariable("headId") Integer headId,
                                                 @RequestBody @NotBlank Article article){
        return passageService.updateArticle(article, headId, userId);
    }

    @DeleteMapping("/headId/{headId}")
    public CommonResponse<Object> deleteArticleByHeadId(@PathVariable("headId") Integer headId){
        return passageService.deleteArticleByPassageHeadId(headId);
    }

    @DeleteMapping("/userId/{userId}")
    public CommonResponse<Object> deleteArticlesByUserId(@PathVariable("userId") Long userId){
        return passageService.deleteArticlesByUserId(userId);
    }

    @GetMapping("/articles")
    public CommonResponse<List<Article>> getAllArticles(){
        return passageService.getAllArticles();
    }

    @GetMapping("/articles/userId/{userId}")
    public CommonResponse<List<Article>> getArticlesByUserId(@PathVariable("userId") Long userId){
        return passageService.getAllArticlesByUserId(userId);
    }

    @GetMapping("/passages/headId/{headId}")
    public CommonResponse<List<Passage>> getPassagesByUserId(@PathVariable("headId") Integer headId){
        return passageService.getPassagesByPassageHead(headId);
    }
}
