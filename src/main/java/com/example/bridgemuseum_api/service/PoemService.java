package com.example.bridgemuseum_api.service;

import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.Poem;

import java.util.List;

public interface PoemService {
    public CommonResponse<Poem> getPoemById(Integer id);

    public CommonResponse<List<Poem>> getPoemsByName(String name);

    public CommonResponse<List<Poem>> getPoemsByPoet(String poet);

    public CommonResponse<List<Poem>> getPoemsByDynasty(String dynasty);

    public CommonResponse<Poem> addPoem(Poem poem);

    public CommonResponse<Poem> modifyPoem(Poem poem);

    public CommonResponse<Poem> deletePoemById(Integer id);

    public CommonResponse<Poem> deletePoemByPoem(Poem poem);
}
