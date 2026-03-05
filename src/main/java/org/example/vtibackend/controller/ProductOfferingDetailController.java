package org.example.vtibackend.controller;

import org.example.vtibackend.dto.request.ProductOfferingDetailRequest;
import org.example.vtibackend.entity.ProductOffering;
import org.example.vtibackend.service.ProductOfferingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductOfferingDetailController {
    @Autowired
    private ProductOfferingDetailService productOfferingDetailService;

    @PostMapping("/assign-product-detail")
    private ResponseEntity<ProductOffering> assignProductDetail(@RequestBody ProductOfferingDetailRequest request){
        ProductOffering productOffering = productOfferingDetailService.assignProductOfferingDetail(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(productOffering);
    }
}
