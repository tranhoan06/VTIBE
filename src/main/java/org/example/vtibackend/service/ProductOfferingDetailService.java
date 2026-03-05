package org.example.vtibackend.service;

import org.example.vtibackend.dto.request.ProductOfferingDetailRequest;
import org.example.vtibackend.entity.ProductOffering;

public interface ProductOfferingDetailService {
    ProductOffering assignProductOfferingDetail(ProductOfferingDetailRequest request);
}
