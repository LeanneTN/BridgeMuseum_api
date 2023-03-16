package com.example.bridgemuseum_api.service;

import com.example.bridgemuseum_api.VO.Address;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.Bridge;

import java.util.List;

public interface BridgeService {
    CommonResponse<Bridge> getBridgeById(int id);

    CommonResponse<List<Bridge>> getBridgesByName(String name);

    CommonResponse<List<Bridge>> getBridgesByUsername(String username);

    CommonResponse<Object> deleteBridgeById(int id);

    CommonResponse<Object> deleteBridgesByUsername(String username);

    CommonResponse<Object> deleteBridgesByUserId(Long userId);

    CommonResponse<Bridge> addBridge(Bridge bridge);

    CommonResponse<Bridge> modifyBridge(Bridge bridge);

    CommonResponse<Address> modifyAddressOfBridgeById(int id, Address address);

    CommonResponse<List<Bridge>> getAllBridges();

    CommonResponse<List<Bridge>> getBridgesByDynasty(String dynasty);

    CommonResponse<List<Bridge>> getBridgesByType(Integer type);

    CommonResponse<List<Bridge>> getBridgesByProvince(String province);

    CommonResponse<List<Bridge>> getBridgesByProvinceAndCity(String province, String city);

    CommonResponse<List<Bridge>> getBridgesByPreciseAddress(Address address);

    CommonResponse<Bridge> modifyDynastyById(String dynasty, Integer id);

    CommonResponse<Bridge> modifyTypeById(Integer type, Integer id);
}
