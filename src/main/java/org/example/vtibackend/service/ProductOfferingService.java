package org.example.vtibackend.service;

import org.example.vtibackend.common.StatusEnum;
import org.example.vtibackend.entity.ProductOffering;

import java.util.List;
import java.util.Optional;

public interface ProductOfferingService {
    ProductOffering getById(Long id);

    List<ProductOffering> getAll();

    List<ProductOffering> findByNameAndColor(String name, String color);

    ProductOffering addProduct(ProductOffering productOffering);

    Optional<ProductOffering> updateProduct(ProductOffering productOffering);

    List<ProductOffering> getFIlter(StatusEnum status, Long price);

    List<ProductOffering> getOfferingDetailGreaterThanTwo();
}
