package org.example.vtibackend.service;

import org.example.vtibackend.entity.ProductOffering;

import java.util.List;

public interface ProductOfferingService {
    ProductOffering getById(Long id);

    List<ProductOffering> getAll();

    List<ProductOffering> findByNameAndColor(String name, String color);
}
