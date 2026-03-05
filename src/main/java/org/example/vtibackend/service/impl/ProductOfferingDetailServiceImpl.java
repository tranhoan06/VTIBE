package org.example.vtibackend.service.impl;

import org.example.vtibackend.dto.request.ProductOfferingDetailRequest;
import org.example.vtibackend.entity.ProductDetail;
import org.example.vtibackend.entity.ProductOffering;
import org.example.vtibackend.entity.ProductOfferingDetail;
import org.example.vtibackend.repository.ProductDetailRepository;
import org.example.vtibackend.repository.ProductOfferingDetailRepository;
import org.example.vtibackend.repository.ProductOfferingRepository;
import org.example.vtibackend.service.ProductOfferingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductOfferingDetailServiceImpl implements ProductOfferingDetailService {
    @Autowired
    private ProductOfferingDetailRepository productOfferingDetailRepository;

    @Autowired
    private ProductOfferingRepository productOfferingRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public ProductOffering assignProductOfferingDetail(ProductOfferingDetailRequest request) {
        ProductOffering productOffering =
                productOfferingRepository.findById(request.getProductOfferingId())
                        .orElseThrow(() -> new RuntimeException("ProductOffering not exist"));

        List<ProductDetail> productDetailList =
                productDetailRepository.findAllById(request.getProductDetailIds());

        if (productDetailList.isEmpty()) {
            throw new RuntimeException("ProductDetailIds not exist");
        }

        List<ProductOfferingDetail> relations = new ArrayList<>();

        for (ProductDetail productDetail : productDetailList) {

            ProductOfferingDetail relation = new ProductOfferingDetail();
            relation.setProductOfferings(productOffering);
            relation.setProductDetail(productDetail);

            relations.add(relation);
        }

        productOfferingDetailRepository.saveAll(relations);
        productOffering.setProductOfferingDetail(relations);
        return productOffering;
    }
}
