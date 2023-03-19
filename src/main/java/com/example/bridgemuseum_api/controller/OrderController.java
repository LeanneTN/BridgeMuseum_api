package com.example.bridgemuseum_api.controller;

import com.example.bridgemuseum_api.VO.OrderAddress;
import com.example.bridgemuseum_api.VO.OrderCartItemVO;
import com.example.bridgemuseum_api.VO.OrderVO;
import com.example.bridgemuseum_api.common.CONSTANT;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.Collection;
import com.example.bridgemuseum_api.domain.Order;
import com.example.bridgemuseum_api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PutMapping("/user_id/{userId}")
    public CommonResponse<OrderVO> addOrder(@RequestBody @NotBlank OrderAddress orderAddress,
                                            @PathVariable("userId") Long userId){
        return orderService.addOrder(userId, orderAddress);
    }

    @GetMapping("/order_no/{orderNo}/user_id/{userId}")
    public CommonResponse<OrderCartItemVO> getOrderDetail(@PathVariable("orderNo") String orderNo,
                                                          @PathVariable("userId") Long userId){
        return orderService.getOrderDetail(userId, orderNo);
    }

    @GetMapping("/orders/user_id/{userId}")
    public CommonResponse<List<OrderVO>> getOrderListByUserId(@PathVariable("userId") Long userId){
        return orderService.getOrderList(userId);
    }

    @PostMapping("/user_id/{userId}/order_id/{orderId}/cancellation")
    public CommonResponse<String> cancelOrder(@PathVariable("userId") Long userId,
                                              @PathVariable("orderId") Long orderId){
        return orderService.cancelOrder(userId, orderId);
    }

    @PostMapping("/order_id/{orderId}/status/open")
    public CommonResponse<String> modifyStatusToOpen(@PathVariable("orderId") Long orderId){
        return orderService.modifyStatus(CONSTANT.ORDER_STATUS.OPEN, orderId);
    }

    @PostMapping("/order_id/{orderId}/status/transportation")
    public CommonResponse<String> modifyStatusToTransportation(@PathVariable("orderId") Long orderId){
        return orderService.modifyStatus(CONSTANT.ORDER_STATUS.TRANSPORT, orderId);
    }

    @PostMapping("/order_id/{orderId}/status/close")
    public CommonResponse<String> modifyStatusToClose(@PathVariable("orderId") Long orderId){
        return orderService.modifyStatus(CONSTANT.ORDER_STATUS.CLOSE, orderId);
    }

    @PostMapping("/order_id/{orderId}/status/End")
    public CommonResponse<String> modifyStatusToEnd(@PathVariable("orderId") Long orderId){
        return orderService.modifyStatus(CONSTANT.ORDER_STATUS.END, orderId);
    }

    @DeleteMapping("/order_id/{orderId}/user_id/{userId}")
    public CommonResponse<Object> deleteOrderInfoByUserId(@PathVariable("orderId") Long orderId,
                                                          @PathVariable("userId") Long userId){
        return orderService.deleteOrderInfoByUserId(orderId, userId);
    }
}
