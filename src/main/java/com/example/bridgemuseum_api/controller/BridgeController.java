package com.example.bridgemuseum_api.controller;

import com.example.bridgemuseum_api.VO.Address;
import com.example.bridgemuseum_api.common.CONSTANT;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.Bridge;
import com.example.bridgemuseum_api.service.BridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author LeanneTN
 * Date: 2023/3/15
 */
@RestController
@RequestMapping("/bridge")
public class BridgeController {
    @Autowired
    private BridgeService bridgeService;

    @GetMapping("/")
    public CommonResponse<List<Bridge>> getAllBridges(){
        return bridgeService.getAllBridges();
    }

    @GetMapping("/id/{id}")
    public CommonResponse<Bridge> getBridgeById(@PathVariable("id") Integer id){
        return bridgeService.getBridgeById(id);
    }

    @GetMapping("/bridges/name/{name}")
    public CommonResponse<List<Bridge>> getBridgeByName(@PathVariable("name") String name){
        return bridgeService.getBridgesByName(name);
    }

    @PutMapping("/")
    public CommonResponse<Bridge> addBridge(@RequestBody @NotBlank Bridge bridge){
        return bridgeService.addBridge(bridge);
    }

    @PostMapping("/")
    public CommonResponse<Bridge> modifyBridge(@RequestBody @NotBlank Bridge bridge){
        return bridgeService.modifyBridge(bridge);
    }

    @GetMapping("/bridges/username/{username}")
    public CommonResponse<List<Bridge>> getBridgesByUsername(@PathVariable("username") String username){
        return bridgeService.getBridgesByUsername(username);
    }

    @DeleteMapping("/id/{id}")
    public CommonResponse<Object> deleteBridgeById(@PathVariable("id") Integer id){
        return bridgeService.deleteBridgeById(id);
    }

    @DeleteMapping("/username/{username}")
    public CommonResponse<Object> deleteBridgesByUsername(@PathVariable("username") String username){
        return bridgeService.deleteBridgesByUsername(username);
    }

    @DeleteMapping("/userId/{userId}")
    public CommonResponse<Object> deleteBridgesByUserId(@PathVariable("userId") Long userId){
        return bridgeService.deleteBridgesByUserId(userId);
    }

    @PostMapping("/id/{id}/address")
    public CommonResponse<Address> modifyAddressById(@PathVariable("id") Integer id,
                                                     @RequestBody @NotBlank Address address){
        return bridgeService.modifyAddressOfBridgeById(id, address);
    }

    @GetMapping("/bridges/dynasty/{dynasty}")
    public CommonResponse<List<Bridge>> getBridgesByDynasty(@PathVariable("dynasty") String dynasty){
        return bridgeService.getBridgesByDynasty(dynasty);
    }

    @GetMapping("/bridges/type/red_bridge")
    public CommonResponse<List<Bridge>> getRedBridges(){
        return bridgeService.getBridgesByType(CONSTANT.BRIDGE_TYPE.RED_BRIDGE_OF_WAR);
    }

    @GetMapping("/bridges/type/historical_bridge")
    public CommonResponse<List<Bridge>> getHistoricalBridges(){
        return bridgeService.getBridgesByType(CONSTANT.BRIDGE_TYPE.HISTORICAL_BRIDGE);
    }
}
