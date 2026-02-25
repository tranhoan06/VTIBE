package org.example.vtibackend.service.impl;

import org.example.vtibackend.entity.ProductOffering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.vtibackend.repository.ProductOfferingRepository;
import org.example.vtibackend.service.ProductOfferingService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductOfferingServiceImpl implements ProductOfferingService {
    @Autowired
    private ProductOfferingRepository productOfferingRepository;

    @Override
    public ProductOffering getById(Long id) {
        Optional<ProductOffering> productOffering = productOfferingRepository.findById(id);
        if(productOffering.isEmpty()) {
            throw new RuntimeException("Khong tim thay product");
        } else {
            return productOffering.get();
        }
    }

    @Override
    public List<ProductOffering> getAll() {
        return productOfferingRepository.findAll();
    }

    @Override
    public List<ProductOffering> findByNameAndColor(String name, String color) {

        if (color == null) {
            return productOfferingRepository.findByName(name);
        }

        return productOfferingRepository.findByNameAndColor(name, color);
    }


}
