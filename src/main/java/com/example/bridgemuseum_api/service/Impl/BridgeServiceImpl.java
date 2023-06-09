package com.example.bridgemuseum_api.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.bridgemuseum_api.VO.Address;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.common.ResponseCode;
import com.example.bridgemuseum_api.domain.Bridge;
import com.example.bridgemuseum_api.domain.User;
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
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), "bridge doesn't exist");
        }
        return CommonResponse.createForSuccess(bridgeList);
    }

    @Override
    public CommonResponse<List<Bridge>> getBridgesByUsername(String username) {
        List<Bridge> bridgeList = bridgeMapper.selectList(Wrappers.<Bridge>query().eq("username", username));
        if(bridgeList.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), "bridge uploaded by " + username + " doesn't exist");
        }
        return CommonResponse.createForSuccess(bridgeList);
    }

    @Override
    public CommonResponse<Object> deleteBridgeById(int id) {
        int role = bridgeMapper.deleteById(id);
        if(role == 0){
            return CommonResponse.createForError("delete bridge failed");
        }
        return CommonResponse.createForSuccess();
    }

    @Override
    public CommonResponse<Object> deleteBridgesByUsername(String username) {
        int role = bridgeMapper.delete(Wrappers.<Bridge>query().eq("username", username));
        if(role == 0){
            return CommonResponse.createForError("delete bridges failed");
        }
        return CommonResponse.createForSuccess();
    }

    @Override
    public CommonResponse<Object> deleteBridgesByUserId(Long userId) {
        int role = bridgeMapper.delete(Wrappers.<Bridge>query().eq("user_id", userId));
        if(role == 0){
            return CommonResponse.createForError("delete bridges failed");
        }
        return CommonResponse.createForSuccess();
    }

    @Override
    public CommonResponse<Bridge> addBridge(Bridge bridge) {
        int role = bridgeMapper.insert(bridge);
        if (role == 0){
            return CommonResponse.createForError("add bridge failed");
        }
        return CommonResponse.createForSuccess(bridge);
    }

    @Override
    public CommonResponse<Bridge> modifyBridge(Bridge bridge) {
        int role = bridgeMapper.updateById(bridge);
        if(role == 0){
            return CommonResponse.createForError("bridge modify failed");
        }
        return CommonResponse.createForSuccess(bridge);
    }

    @Override
    public CommonResponse<Address> modifyAddressOfBridgeById(int id, Address address) {
        Bridge bridge = bridgeMapper.selectById(id);
        if(bridge == null){
            return CommonResponse.createForError("bridge doesn't exist");
        }
        if(address.getProvince() != null){
            bridge.setProvince(address.getProvince());
        }
        if(address.getCity() != null){
            bridge.setCity(address.getCity());
        }
        if(address.getPreciseAddress() != null){
            bridge.setPreciseAddress(address.getPreciseAddress());
        }
        int role = bridgeMapper.updateById(bridge);
        if (role == 0){
            return CommonResponse.createForError("address update failed");
        }
        return CommonResponse.createForSuccess(address);
    }

    @Override
    public CommonResponse<List<Bridge>> getAllBridges() {
        List<Bridge> bridgeList = bridgeMapper.selectList(null);
        return CommonResponse.createForSuccess(bridgeList);
    }

    @Override
    public CommonResponse<List<Bridge>> getBridgesByDynasty(String dynasty) {
        List<Bridge> bridgeList = bridgeMapper.selectList(Wrappers.<Bridge>query().eq("dynasty", dynasty));
        if(bridgeList.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDescription());
        }
        return CommonResponse.createForSuccess(bridgeList);
    }

    @Override
    public CommonResponse<List<Bridge>> getBridgesByType(Integer type) {
        List<Bridge> bridgeList = bridgeMapper.selectList(Wrappers.<Bridge>query().eq("type", type));
        if(bridgeList.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDescription());
        }
        return CommonResponse.createForSuccess(bridgeList);
    }

    @Override
    public CommonResponse<List<Bridge>> getBridgesByProvince(String province) {
        List<Bridge> bridgeList = bridgeMapper.selectList(Wrappers.<Bridge>query().eq("province", province));
        if(bridgeList.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDescription());
        }
        return CommonResponse.createForSuccess(bridgeList);
    }

    @Override
    public CommonResponse<List<Bridge>> getBridgesByProvinceAndCity(String province, String city) {
        List<Bridge> bridgeList = bridgeMapper.selectList(Wrappers.<Bridge>query().eq("province", province)
                .eq("city", city));
        if(bridgeList.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDescription());
        }
        return CommonResponse.createForSuccess(bridgeList);
    }

    @Override
    public CommonResponse<List<Bridge>> getBridgesByPreciseAddress(Address address) {
        String province = address.getProvince();
        String city = address.getCity();
        String preciseAddress = address.getPreciseAddress();
        List<Bridge> bridgeList = bridgeMapper.selectList(Wrappers.<Bridge>query().eq("province", province)
                .eq("city", city).eq("address", preciseAddress));
        if(bridgeList.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDescription());
        }
        return CommonResponse.createForSuccess(bridgeList);
    }

    @Override
    public CommonResponse<Bridge> modifyDynastyById(String dynasty, Integer id) {
        Bridge bridge = bridgeMapper.selectById(id);
        if (bridge == null){
            return CommonResponse.createForError("bridge doesn't exist");
        }
        bridge.setDynasty(dynasty);
        int role = bridgeMapper.updateById(bridge);
        if(role == 0){
            return CommonResponse.createForError("dynasty update failed");
        }
        return CommonResponse.createForSuccess(bridge);
    }

    @Override
    public CommonResponse<Bridge> modifyTypeById(Integer type, Integer id) {
        Bridge bridge = bridgeMapper.selectById(id);
        if (bridge == null){
            return CommonResponse.createForError("bridge doesn't exist");
        }
        bridge.setBridgeType(type);
        int role = bridgeMapper.updateById(bridge);
        if(role == 0){
            return CommonResponse.createForError("bridge type update failed");
        }
        return CommonResponse.createForSuccess(bridge);
    }
}
