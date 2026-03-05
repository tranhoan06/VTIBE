package org.example.vtibackend.service.impl;

import org.example.vtibackend.common.StatusEnum;
import org.example.vtibackend.dto.request.ProductDetailRequest;
import org.example.vtibackend.dto.response.ProductDetailResponse;
import org.example.vtibackend.dto.response.ProductOfferingResponse;
import org.example.vtibackend.entity.ProductDetail;
import org.example.vtibackend.entity.ProductOffering;
import org.example.vtibackend.repository.ProductDetailRepository;
import org.example.vtibackend.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public ProductDetailResponse addProductDetail(ProductDetailRequest productDetailRequest) {

        ProductDetail product = new ProductDetail();

        product.setWeight(productDetailRequest.getWeight());
        product.setFeature(productDetailRequest.getFeature());
        product.setPower(productDetailRequest.getPower());
        product.setBrand(productDetailRequest.getBrand());
        product.setImage(productDetailRequest.getImage());
        product.setVideo(productDetailRequest.getVideo());

        ProductDetail saved = productDetailRepository.save(product);

        return new ProductDetailResponse(
                saved.getId(),
                saved.getWeight(),
                saved.getFeature(),
                saved.getPower(),
                saved.getBrand(),
                saved.getImage(),
                saved.getVideo()
        );
    }
}
