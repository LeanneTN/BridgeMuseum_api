package com.example.bridgemuseum_api.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.common.ResponseCode;
import com.example.bridgemuseum_api.domain.Collection;
import com.example.bridgemuseum_api.mapper.CollectionMapper;
import com.example.bridgemuseum_api.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("collectionService")
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;

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
}
