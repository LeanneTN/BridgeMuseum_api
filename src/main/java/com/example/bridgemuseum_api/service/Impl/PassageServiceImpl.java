package com.example.bridgemuseum_api.service.Impl;

import com.example.bridgemuseum_api.mapper.PassageMapper;
import com.example.bridgemuseum_api.service.PassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("passageService")
public class PassageServiceImpl implements PassageService {
    @Autowired
    private PassageMapper passageMapper;
}
