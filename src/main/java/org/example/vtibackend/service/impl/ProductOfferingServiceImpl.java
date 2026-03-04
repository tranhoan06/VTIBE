package org.example.vtibackend.service.impl;

import org.example.vtibackend.common.StatusEnum;
import org.example.vtibackend.dto.request.ProductOfferingRequest;
import org.example.vtibackend.dto.response.ProductOfferingResponse;
import org.example.vtibackend.entity.ProductOffering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.vtibackend.repository.ProductOfferingRepository;
import org.example.vtibackend.service.ProductOfferingService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductOfferingServiceImpl implements ProductOfferingService {
    @Autowired
    private ProductOfferingRepository productOfferingRepository;

    @Override
    public ProductOffering getById(Long id) {
        Optional<ProductOffering> productOffering = productOfferingRepository.findById(id);
        if (productOffering.isEmpty()) {
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

    @Override
    public ProductOfferingResponse addProduct(ProductOfferingRequest productOfferingRequest) {

        ProductOffering product = new ProductOffering();

        product.setName(productOfferingRequest.getName());
        product.setPrice(productOfferingRequest.getPrice());
        product.setColor(productOfferingRequest.getColor());
        product.setStatus(StatusEnum.ACTIVE);

        ProductOffering saved = productOfferingRepository.save(product);

        return new ProductOfferingResponse(
                saved.getId(),
                saved.getName(),
                saved.getPrice(),
                saved.getColor(),
                saved.getStatus()
        );
    }

    @Override
    public Optional<ProductOffering> updateProduct(ProductOffering productOffering) {

        ProductOffering product = productOfferingRepository
                .findById(productOffering.getId())
                .orElseThrow(() -> new RuntimeException("Khong tim thay san pham can cap nhat"));

        product.setName(productOffering.getName());

        product.setColor(productOffering.getColor());

        product.setPrice(productOffering.getPrice());

        product.setStatus(productOffering.getStatus());

        productOfferingRepository.save(product);

        return Optional.of(product);
    }

    @Override
    public List<ProductOffering> getFIlter(StatusEnum status, Long price) {
        return productOfferingRepository.getFilter(status, price);
    }

    @Override
    public List<ProductOffering> getOfferingDetailGreaterThanTwo() {
        return productOfferingRepository.getOfferingDetailGreaterThanTwo();
    }


}
