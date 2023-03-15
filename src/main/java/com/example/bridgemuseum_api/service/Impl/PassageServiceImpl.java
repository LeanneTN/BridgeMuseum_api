package com.example.bridgemuseum_api.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.bridgemuseum_api.VO.Article;
import com.example.bridgemuseum_api.common.CONSTANT;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.common.ResponseCode;
import com.example.bridgemuseum_api.domain.Passage;
import com.example.bridgemuseum_api.mapper.PassageMapper;
import com.example.bridgemuseum_api.service.PassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LeanneTN
 * date: 2023/3/15
 */
@Service("passageService")
public class PassageServiceImpl implements PassageService {
    @Autowired
    private PassageMapper passageMapper;

    @Override
    public CommonResponse<List<Passage>> getPassagesByPassageHead(Integer passageHeadId) {
        List<Passage> passages = passageMapper.selectList(Wrappers.<Passage>query().eq("passage_head_id", passageHeadId));
        if(passages.isEmpty()){
            return CommonResponse.createForError("passage is empty");
        }
        return CommonResponse.createForSuccess(passages);
    }

    @Override
    public CommonResponse<Article> getArticleByPassageHead(Integer passageHeadId) {
        List<Passage> passages = passageMapper.selectList(Wrappers.<Passage>query().eq("passage_head_id", passageHeadId));
        if(passages.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), "passage is empty");
        }
        Article article = new Article();
        List<String> paragraphs = new ArrayList<>();
        int numOfParagraphs = 0;
        for (Passage passage : passages){
            paragraphs.add(passage.getContent());
            numOfParagraphs++;
        }
        article.setParagraphs(paragraphs);
        article.setNumOfParas(numOfParagraphs);
        return CommonResponse.createForSuccess(article);
    }

    @Override
    public CommonResponse<List<Article>> getAllArticlesByUserId(Integer userId) {
        List<Integer> headIds = getAllPassageHeadByUserId(userId).getData();
        if(headIds.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), "passages of this user id is empty");
        }
        List<Article> articles = new ArrayList<>();
        for(Integer headId : headIds){
            Article article = getArticleByPassageHead(headId).getData();
            articles.add(article);
        }
        return CommonResponse.createForSuccess(articles);
    }

    @Override
    public CommonResponse<List<Article>> getAllArticles() {
        List<Integer> headIds = new ArrayList<>();
        List<Passage> passages = passageMapper.selectList(Wrappers.<Passage>query().eq("if_head", CONSTANT.PASSAGE.HEAD));
        if (passages.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), "article is empty");
        }
        for (Passage passage : passages){
            headIds.add(passage.getId());
        }
        List<Article> articles = new ArrayList<>();
        for (int headId : headIds){
            Article article = getArticleByPassageHead(headId).getData();
            articles.add(article);
        }
        return CommonResponse.createForSuccess(articles);
    }

    @Override
    public CommonResponse<List<Integer>> getAllPassageHeadByUserId(Integer userId) {
        List<Passage> passages = passageMapper.selectList(Wrappers.<Passage>query().eq("user_id", userId)
                                                                    .eq("if_head", CONSTANT.PASSAGE.HEAD));
        if(passages.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), "passage of this user id is empty");
        }
        List<Integer> passageHeadIds = new ArrayList<>();
        for (Passage passage : passages){
            passageHeadIds.add(passage.getId());
        }
        return CommonResponse.createForSuccess(passageHeadIds);
    }

    @Override
    public CommonResponse<Article> addArticle(Article article, Long userId) {
        int headId = 0;
        for(int i = 0; i < article.getNumOfParas(); i++){
            if(article.getParagraphs().get(i).length() >= 255){
                return CommonResponse.createForError(ResponseCode.PASSAGE_OVER_BOUND.getCode(),
                        ResponseCode.PASSAGE_OVER_BOUND.getDescription());
            }
            if (i == 0){
                Passage passage = new Passage();
                passage.setContent(article.getParagraphs().get(i));
                passage.setUserId(userId);
                passage.setIfHead(CONSTANT.PASSAGE.HEAD);
                headId = passageMapper.insert(passage);
                passage.setPassageHeadId(headId);
                passageMapper.update(passage, null);
            }else{
                Passage passage = new Passage();
                passage.setContent(article.getParagraphs().get(i));
                passage.setUserId(userId);
                passage.setIfHead(CONSTANT.PASSAGE.HEAD);
                passage.setPassageHeadId(headId);
                int flag = passageMapper.insert(passage);
                if(flag == 0){
                    return CommonResponse.createForError("add article failed");
                }
            }
        }
        return CommonResponse.createForSuccess(article);
    }

    @Override
    public CommonResponse<Article> updateArticle(Article article) {
        return null;
    }

    @Override
    public CommonResponse<Object> deleteArticleByPassageHeadId(Integer passageHeadId) {
        return null;
    }

    @Override
    public CommonResponse<Object> deleteArticlesByUserId(Integer userId) {
        return null;
    }
}
