package org.example.vtibackend.service;


import org.example.vtibackend.dto.request.ProductDetailRequest;
import org.example.vtibackend.dto.response.ProductDetailResponse;

public interface ProductDetailService {
    ProductDetailResponse addProductDetail(ProductDetailRequest productDetailRequest);
}
