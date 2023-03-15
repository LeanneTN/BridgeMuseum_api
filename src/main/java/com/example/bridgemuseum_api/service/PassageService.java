package com.example.bridgemuseum_api.service;

import com.example.bridgemuseum_api.VO.Article;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.Passage;

import java.util.List;

public interface PassageService {
    CommonResponse<List<Passage>> getPassagesByPassageHead(Integer passageHeadId);

    CommonResponse<Article> getArticleByPassageHead(Integer passageHeadId);

    CommonResponse<List<Article>> getAllArticlesByUserId(Integer userId);

    CommonResponse<List<Article>> getAllArticles();

    CommonResponse<List<Integer>> getAllPassageHeadByUserId(Integer userId);

    CommonResponse<Article> addArticle(Article article, Long userId);

    CommonResponse<Article> updateArticle(Article article);

    CommonResponse<Object> deleteArticleByPassageHeadId(Integer passageHeadId);

    CommonResponse<Object> deleteArticlesByUserId(Integer userId);

}
