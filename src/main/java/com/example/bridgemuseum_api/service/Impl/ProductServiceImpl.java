package com.example.bridgemuseum_api.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.common.ResponseCode;
import com.example.bridgemuseum_api.domain.Product;
import com.example.bridgemuseum_api.mapper.ProductMapper;
import com.example.bridgemuseum_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public CommonResponse<Product> addProduct(Product product) {
        int role = productMapper.insert(product);
        if(role == 0){
            return CommonResponse.createForError("add product failed");
        }
        return CommonResponse.createForSuccess(product);
    }

    @Override
    public CommonResponse<Product> modifyProduct(Product product) {
        int role = productMapper.update(product, null);
        if(role == 0){
            return CommonResponse.createForError("update product failed");
        }
        return CommonResponse.createForSuccess(product);
    }

    @Override
    public CommonResponse<Object> deleteProduct(Product product) {
        int role = productMapper.deleteById(product);
        if(role == 0){
            return CommonResponse.createForError("delete product failed");
        }
        return CommonResponse.createForSuccess(product);
    }

    @Override
    public CommonResponse<Product> getProductById(Integer id) {
        Product product = productMapper.selectById(id);
        if (product == null){
            return CommonResponse.createForError("product doesn't exist");
        }
        return CommonResponse.createForSuccess(product);
    }

    @Override
    public CommonResponse<List<Product>> getProductsByCategory(Integer category) {
        List<Product> products = productMapper.selectList(Wrappers.<Product>query()
                .eq("category", category));
        if (products.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.SUCCESS.getDescription());
        }
        return CommonResponse.createForSuccess(products);
    }

    @Override
    public CommonResponse<List<Product>> getProductsByName(String name) {
        List<Product> products = productMapper.selectList(Wrappers.<Product>query()
                .eq("name", name));
        if (products.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.SUCCESS.getDescription());
        }
        return CommonResponse.createForSuccess(products);
    }
}
