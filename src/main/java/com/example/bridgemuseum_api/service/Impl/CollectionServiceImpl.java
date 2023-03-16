package com.example.bridgemuseum_api.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.bridgemuseum_api.VO.Article;
import com.example.bridgemuseum_api.common.CONSTANT;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.common.ResponseCode;
import com.example.bridgemuseum_api.domain.Bridge;
import com.example.bridgemuseum_api.domain.Collection;
import com.example.bridgemuseum_api.domain.Poem;
import com.example.bridgemuseum_api.domain.Product;
import com.example.bridgemuseum_api.mapper.CollectionMapper;
import com.example.bridgemuseum_api.mapper.PassageMapper;
import com.example.bridgemuseum_api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("collectionService")
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;

    @Autowired
    private PassageService passageService;

    @Autowired
    private BridgeService bridgeService;

    @Autowired
    private PoemService poemService;

    @Autowired
    private ProductService productService;

    @Override
    public CommonResponse<Collection> addCollection(Collection collection) {
        int role = collectionMapper.insert(collection);
        if(role == 0){
            return CommonResponse.createForError("add collection failed");
        }
        return CommonResponse.createForSuccess(collection);
    }

    @Override
    public CommonResponse<Collection> updateCollectionByCollection(Collection collection) {
        int role = collectionMapper.update(collection, null);
        if(role == 0){
            return CommonResponse.createForError("update collection failed");
        }
        return CommonResponse.createForSuccess(collection);
    }

    @Override
    public CommonResponse<List<Collection>> getAllCollectionsByUserId(Long userId) {
        List<Collection> collections = collectionMapper.selectList(Wrappers.<Collection>query().eq("user_id", userId));
        if(collections.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDescription());
        }
        return CommonResponse.createForSuccess(collections);
    }

    @Override
    public CommonResponse<List<Collection>> getCollectionsByTypeAndUserId(Integer type, Long userId) {
        List<Collection> collections = collectionMapper.selectList(Wrappers.<Collection>query().eq("user_id", userId)
                .eq("type", type));
        if(collections.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDescription());
        }
        return CommonResponse.createForSuccess(collections);
    }

    @Override
    public CommonResponse<Object> deleteCollectionById(Long id) {
        int role = collectionMapper.deleteById(id);
        if (role == 0){
            return CommonResponse.createForError("delete collection item failed");
        }
        return CommonResponse.createForSuccess();
    }

    @Override
    public CommonResponse<Object> deleteCollectionByCollection(Collection collection) {
        int role = collectionMapper.deleteById(collection);
        if (role == 0){
            return CommonResponse.createForError("delete collection item failed");
        }
        return CommonResponse.createForSuccess();
    }

    @Override
    public CommonResponse<Object> deleteCollectionsByUserId(Long userId) {
        int role = collectionMapper.delete(Wrappers.<Collection>query().eq("user_id", userId));
        if (role == 0){
            return CommonResponse.createForError("delete collection item failed");
        }
        return CommonResponse.createForSuccess();
    }

    @Override
    public CommonResponse<Object> deleteCollectionsByTypeAndUserId(Integer type, Long userId) {
        int role = collectionMapper.delete(Wrappers.<Collection>query().eq("user_id", userId)
                .eq("type", type));
        if (role == 0){
            return CommonResponse.createForError("delete collection item failed");
        }
        return CommonResponse.createForSuccess();
    }

    @Override
    public CommonResponse<List<Bridge>> getBridgesFromCollection(Long userId) {
        List<Collection> collections = collectionMapper.selectList(Wrappers.<Collection>query()
                .eq("user_id", userId).eq("type", CONSTANT.COLLECTION_ITEM.BRIDGE));
        List<Bridge> bridgeList = new ArrayList<>();
        for (Collection collection: collections){
            Bridge bridge = bridgeService.getBridgeById(collection.getIdOfItem()).getData();
            bridgeList.add(bridge);
        }
        if(bridgeList.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDescription());
        }
        return CommonResponse.createForSuccess(bridgeList);
    }

    @Override
    public CommonResponse<List<Poem>> getPoemsFromCollection(Long userId) {
        List<Collection> collections = collectionMapper.selectList(Wrappers.<Collection>query()
                .eq("user_id", userId).eq("type", CONSTANT.COLLECTION_ITEM.POEM));
        List<Poem> poems = new ArrayList<>();
        for (Collection collection : collections){
            Poem poem = poemService.getPoemById(collection.getIdOfItem()).getData();
            poems.add(poem);
        }
        if (poems.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDescription());
        }
        return CommonResponse.createForSuccess(poems);
    }

    @Override
    public CommonResponse<List<Article>> getArticlesFromCollection(Long userId) {
        List<Collection> collections = collectionMapper.selectList(Wrappers.<Collection>query()
                .eq("user_id", userId).eq("type", CONSTANT.COLLECTION_ITEM.ARTICLE));
        List<Article> articles = new ArrayList<>();
        for (Collection collection: collections){
            Article article = passageService.getArticleByPassageHead(collection.getIdOfItem()).getData();
            articles.add(article);
        }
        if (articles.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDescription());
        }
        return CommonResponse.createForSuccess(articles);
    }

    @Override
    public CommonResponse<List<Product>> getProductsFromCollection(Long userId) {
        List<Collection> collections = collectionMapper.selectList(Wrappers.<Collection>query()
                .eq("user_id", userId).eq("type", CONSTANT.COLLECTION_ITEM.PRODUCT));
        List<Product> products = new ArrayList<>();
        for (Collection collection : collections){
            Product product = productService.getProductById(collection.getIdOfItem()).getData();
            products.add(product);
        }
        if (products.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDescription());
        }
        return CommonResponse.createForSuccess(products);
    }
}
