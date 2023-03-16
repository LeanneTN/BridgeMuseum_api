package com.example.bridgemuseum_api.service;

import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.Collection;

import java.util.List;

public interface CollectionService {
    CommonResponse<Collection> addCollection(Collection collection);

    CommonResponse<Collection> updateCollectionByCollection(Collection collection);

    CommonResponse<List<Collection>> getAllCollectionsByUserId(Long userId);

    CommonResponse<List<Collection>> getCollectionsByTypeAndUserId(Integer type, Long userId);

    CommonResponse<Object> deleteCollectionById(Long id);

    CommonResponse<Object> deleteCollectionByCollection(Collection collection);

    CommonResponse<Object> deleteCollectionsByUserId(Long userId);

    CommonResponse<Object> deleteCollectionsByTypeAndUserId(Integer type, Long userId);
}
