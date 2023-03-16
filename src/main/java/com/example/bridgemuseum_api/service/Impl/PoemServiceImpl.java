package com.example.bridgemuseum_api.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.common.ResponseCode;
import com.example.bridgemuseum_api.domain.Poem;
import com.example.bridgemuseum_api.mapper.PoemMapper;
import com.example.bridgemuseum_api.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.WeakHashMap;

/**
 * @author LeanneTN
 * date: 2023/3/16
 */
@Service("poemService")
public class PoemServiceImpl implements PoemService {
    @Autowired
    private PoemMapper poemMapper;

    @Override
    public CommonResponse<Poem> getPoemById(Integer id) {
        Poem poem = poemMapper.selectById(id);
        if (poem == null){
            return CommonResponse.createForError("poem id doesn't exist");
        }
        return CommonResponse.createForSuccess(poem);
    }

    @Override
    public CommonResponse<List<Poem>> getPoemsByName(String name) {
        List<Poem> poems = poemMapper.selectList(Wrappers.<Poem>query().eq("name", name));
        if (poems.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDescription());
        }
        return CommonResponse.createForSuccess(poems);
    }

    @Override
    public CommonResponse<List<Poem>> getPoemsByPoet(String poet) {
        List<Poem> poems = poemMapper.selectList(Wrappers.<Poem>query().eq("poet", poet));
        if (poems.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDescription());
        }
        return CommonResponse.createForSuccess(poems);
    }

    @Override
    public CommonResponse<List<Poem>> getPoemsByDynasty(String dynasty) {
        List<Poem> poems = poemMapper.selectList(Wrappers.<Poem>query().eq("dynasty", dynasty));
        if (poems.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDescription());
        }
        return CommonResponse.createForSuccess(poems);
    }

    @Override
    public CommonResponse<Poem> addPoem(Poem poem) {
        int role = poemMapper.insert(poem);
        if (role == 0){
            return CommonResponse.createForError("add poem failed");
        }
        System.out.println(poem.getId());
        return CommonResponse.createForSuccess(poem);
    }

    @Override
    public CommonResponse<Poem> modifyPoem(Poem poem) {
        int role = poemMapper.update(poem, null);
        if(role == 0){
            return CommonResponse.createForError("update poem failed");
        }
        return CommonResponse.createForSuccess(poem);
    }

    @Override
    public CommonResponse<Poem> deletePoemById(Integer id) {
        int role = poemMapper.deleteById(id);
        if(role == 0){
            return CommonResponse.createForError("delete poem " + id + " failed");
        }
        return CommonResponse.createForSuccess();
    }

    @Override
    public CommonResponse<Poem> deletePoemByPoem(Poem poem) {
        int role = poemMapper.deleteById(poem);
        if(role == 0){
            return CommonResponse.createForError("delete poem failed");
        }
        return CommonResponse.createForSuccess(poem);
    }
}
