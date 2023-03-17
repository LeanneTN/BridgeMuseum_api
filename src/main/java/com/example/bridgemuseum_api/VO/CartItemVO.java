package com.example.bridgemuseum_api.VO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemVO {
    private Integer id;
    private Integer productId;
    private Long userId;
    private Integer quantity;
    private Integer checked;

    private String productName;;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productMainImage;

    private BigDecimal cartItemTotalPrice;
    //quantity is being processed by back-end or not
    private Boolean checkStock;
}
