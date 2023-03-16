package com.example.bridgemuseum_api.service;

import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.Product;

import java.util.List;

public interface ProductService {
    public CommonResponse<Product> addProduct(Product product);

    public CommonResponse<Product> modifyProduct(Product product);

    public CommonResponse<Object> deleteProduct(Product product);

    public CommonResponse<Product> getProductById(Integer id);

    public CommonResponse<List<Product>> getProductsByCategory(Integer category);

    public CommonResponse<List<Product>> getProductsByName(String name);
}
