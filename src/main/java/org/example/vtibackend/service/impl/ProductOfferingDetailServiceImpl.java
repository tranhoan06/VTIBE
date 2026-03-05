package org.example.vtibackend.service.impl;

import jakarta.transaction.Transactional;
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

import java.util.*;

@Service
public class ProductOfferingDetailServiceImpl implements ProductOfferingDetailService {
    @Autowired
    private ProductOfferingDetailRepository productOfferingDetailRepository;

    @Autowired
    private ProductOfferingRepository productOfferingRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Transactional
    @Override
    public ProductOffering assignProductOfferingDetail(ProductOfferingDetailRequest request) {
        List<Long> productDetails = new LinkedList<>(new HashSet<>(request.getProductDetailIds()));

        ProductOffering productOffering =
                productOfferingRepository.findById(request.getProductOfferingId())
                        .orElseThrow(() -> new RuntimeException("ProductOffering not exist"));

        List<ProductDetail> productDetailList =
                productDetailRepository.findAllById(productDetails);

        if (productDetailList.isEmpty()) {
            throw new RuntimeException("ProductDetailIds not exist");
        }
        productOfferingDetailRepository.deleteByProductOfferings(productOffering);
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
