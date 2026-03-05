package org.example.vtibackend.controller;

import org.example.vtibackend.dto.request.ProductDetailRequest;
import org.example.vtibackend.dto.response.ProductDetailResponse;
import org.example.vtibackend.entity.ProductDetail;
import org.example.vtibackend.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;

    @PostMapping("/add-product-detail")
    private ResponseEntity<ProductDetailResponse> addProductDetail (@RequestBody ProductDetailRequest productDetailRequest) {
        ProductDetailResponse productDetailResponse = productDetailService.addProductDetail(productDetailRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDetailResponse);
    }
}
