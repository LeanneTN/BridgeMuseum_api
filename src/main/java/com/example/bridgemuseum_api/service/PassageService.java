package com.example.bridgemuseum_api.service;

import com.example.bridgemuseum_api.VO.Article;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.Passage;

import java.util.List;

public interface PassageService {
    CommonResponse<List<Passage>> getPassagesByPassageHead(Integer passageHeadId);

    CommonResponse<Article> getArticleByPassageHead(Integer passageHeadId);

    CommonResponse<List<Article>> getAllArticlesByUserId(Long userId);

    CommonResponse<List<Article>> getAllArticles();

    CommonResponse<List<Integer>> getAllPassageHeadByUserId(Long userId);

    CommonResponse<Article> addArticle(Article article, Long userId);

    CommonResponse<Article> updateArticle(Article article, Integer headId, Long userId);

    CommonResponse<Object> deleteArticleByPassageHeadId(Integer passageHeadId);

    CommonResponse<Object> deleteArticlesByUserId(Long userId);

}
