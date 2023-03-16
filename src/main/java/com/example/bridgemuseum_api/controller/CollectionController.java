package com.example.bridgemuseum_api.controller;

import com.example.bridgemuseum_api.VO.Article;
import com.example.bridgemuseum_api.common.CONSTANT;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.Bridge;
import com.example.bridgemuseum_api.domain.Collection;
import com.example.bridgemuseum_api.domain.Poem;
import com.example.bridgemuseum_api.service.BridgeService;
import com.example.bridgemuseum_api.service.CollectionService;
import com.example.bridgemuseum_api.service.PassageService;
import com.example.bridgemuseum_api.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    //todo: add collection service for shopping module

    @GetMapping("/collections/user_id/{userId}")
    public CommonResponse<List<Collection>> getAllCollectionsByUserId(@PathVariable("userId") Long userId){
        return collectionService.getAllCollectionsByUserId(userId);
    }

    @PutMapping("/")
    public CommonResponse<Collection> addCollection(@RequestBody @NotBlank Collection collection){
        return collectionService.addCollection(collection);
    }

    @PostMapping("/")
    public CommonResponse<Collection> updateCollectionByCollection(@RequestBody @NotBlank Collection collection){
        return collectionService.updateCollectionByCollection(collection);
    }

    @GetMapping("/collections/user_id/{userId}/type/{type}")
    public CommonResponse<List<Collection>> getCollectionsByUserIdAndType(@PathVariable("userId") Long userId,
                                                                          @PathVariable("type") Integer type){
        return collectionService.getCollectionsByTypeAndUserId(type, userId);
    }

    @GetMapping("/collections/user_id/{userId}/products")
    public CommonResponse<List<Collection>> getProductCollectionsByUserId(@PathVariable("userId") Long userId){
        return collectionService.getCollectionsByTypeAndUserId(CONSTANT.COLLECTION_ITEM.PRODUCT, userId);
    }

    @DeleteMapping("/id/{id}")
    public CommonResponse<Object> deleteCollectionById(@PathVariable("id") Long id){
        return collectionService.deleteCollectionById(id);
    }

    @DeleteMapping("/")
    public CommonResponse<Object> deleteCollectionByCollection(@RequestBody @NotBlank Collection collection){
        return collectionService.deleteCollectionByCollection(collection);
    }

    @DeleteMapping("collections/user_id/{userId}")
    public CommonResponse<Object> deleteCollectionsByUserId(@PathVariable("userId") Long userId){
        return collectionService.deleteCollectionsByUserId(userId);
    }

    @DeleteMapping("collections/user_id/{userId}/type/{type}")
    public CommonResponse<Object> deleteCollectionByUserIdAndType(@PathVariable("userId") Long userId,
                                                                  @PathVariable("type") Integer type){
        return collectionService.deleteCollectionsByTypeAndUserId(type, userId);
    }

    @GetMapping("/bridges/user_id/{userId}")
    public CommonResponse<List<Bridge>> getBridgesFromCollectionByUserId(@PathVariable("userId") Long userId){
        return collectionService.getBridgesFromCollection(userId);
    }

    @GetMapping("/poems/user_id/{userId}")
    public CommonResponse<List<Poem>> getPoemsFromCollectionByUserId(@PathVariable("userId") Long userId){
        return collectionService.getPoemsFromCollection(userId);
    }

    @GetMapping("/articles/user_id/{userId}")
    public CommonResponse<List<Article>> getArticlesFromCollectionByUserId(@PathVariable("userId") Long userId){
        return collectionService.getArticlesFromCollection(userId);
    }

}
