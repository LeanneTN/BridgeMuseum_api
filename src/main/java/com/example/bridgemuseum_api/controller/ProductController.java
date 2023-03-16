package com.example.bridgemuseum_api.controller;

import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.Product;
import com.example.bridgemuseum_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PutMapping("/")
    public CommonResponse<Product> addProduct(@RequestBody @NotBlank Product product){
        return productService.addProduct(product);
    }

    @PostMapping("/")
    public CommonResponse<Product> modifyProduct(@RequestBody @NotBlank Product product){
        return productService.modifyProduct(product);
    }

    @DeleteMapping("/")
    public CommonResponse<Object> deleteProductByProduct(@RequestBody @NotBlank Product product){
        return productService.deleteProduct(product);
    }

    @GetMapping("/id/{id}")
    public CommonResponse<Product> getProductById(@PathVariable("id") Integer id){
        return productService.getProductById(id);
    }

    @GetMapping("/products/category/{category}")
    public CommonResponse<List<Product>> getProductsByCategory(@PathVariable("category") Integer category){
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/products/name/{name}")
    public CommonResponse<List<Product>> getProductsByName(@PathVariable("name") String name){
        return productService.getProductsByName(name);
    }
}
