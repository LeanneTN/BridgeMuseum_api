package com.example.bridgemuseum_api.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.bridgemuseum_api.VO.Address;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.Bridge;
import com.example.bridgemuseum_api.mapper.BridgeMapper;
import com.example.bridgemuseum_api.service.BridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LeanneTN
 * date: 2023/3/15
 */
@Service("bridgeService")
public class BridgeServiceImpl implements BridgeService {
    @Autowired
    private BridgeMapper bridgeMapper;

    @Override
    public CommonResponse<Bridge> getBridgeById(int id) {
        Bridge bridge = bridgeMapper.selectById(id);
        if(bridge == null){
            return CommonResponse.createForError("bridge doesn't exist");
        }
        return CommonResponse.createForSuccess(bridge);
    }

    @Override
    public CommonResponse<List<Bridge>> getBridgesByName(String name) {
        List<Bridge> bridgeList = bridgeMapper.selectList(Wrappers.<Bridge>query().eq("name", name));
        if(bridgeList.isEmpty()){
            return CommonResponse.createForError("bridge doesn't exist");
        }
        return CommonResponse.createForSuccess(bridgeList);
    }

    @Override
    public CommonResponse<List<Bridge>> getBridgesByUsername(String username) {
        return null;
    }

    @Override
    public CommonResponse<Object> deleteBridgeById(int id) {
        return null;
    }

    @Override
    public CommonResponse<Object> deleteBridgesByUsername(String username) {
        return null;
    }

    @Override
    public CommonResponse<Bridge> addBridge(Bridge bridge) {
        return null;
    }

    @Override
    public CommonResponse<Bridge> modifyBridge(Bridge bridge) {
        return null;
    }

    @Override
    public CommonResponse<Address> modifyAddressOfBridgeById(int id, Address address) {
        return null;
    }

    @Override
    public CommonResponse<List<Bridge>> getAllBridges() {
        return null;
    }
}
