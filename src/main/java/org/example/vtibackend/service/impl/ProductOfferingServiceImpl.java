package org.example.vtibackend.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.vtibackend.common.StatusEnum;
import org.example.vtibackend.dto.request.ProductOfferingRequest;
import org.example.vtibackend.dto.response.ProductOfferingResponse;
import org.example.vtibackend.entity.ProductOffering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.vtibackend.repository.ProductOfferingRepository;
import org.example.vtibackend.service.ProductOfferingService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductOfferingServiceImpl implements ProductOfferingService {
    @Autowired
    private EntityManager entityManager;

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

    @Override
    public List<ProductOffering> filter(String name, Long minPrice, Long maxPrice, String color, StatusEnum status) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // ProductOffering.class: Trả về đối tượng Class đại diện cho ProductOffering
        // Tạo query để lấy dữ liệu kiểu ProductOffering
        CriteriaQuery<ProductOffering> query = criteriaBuilder.createQuery(ProductOffering.class);
        // Khai báo bảng
        // Root đại diện cho entity (table) trong câu query
        Root<ProductOffering> root = query.from(ProductOffering.class);

        List<Predicate> predicates = new ArrayList<>();

        if(name != null && !name.isEmpty()) {
            Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + name + "%");
            predicates.add(predicate);
        }

        if(minPrice != null) {
            Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            predicates.add(predicate);
        }

        if(maxPrice != null) {
            Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
            predicates.add(predicate);
        }

        if(color != null && !color.isEmpty()) {
            Predicate predicate = criteriaBuilder.like(root.get("color"), "%" + color + "%");
            predicates.add(predicate);
        }

        if(status != null) {
            Predicate predicate = criteriaBuilder.equal(root.get("status"), status);
            predicates.add(predicate);
        }

        // Gắn điều kieejn where
        query.where(predicates.toArray(new  Predicate[0]));
        // Tạo query từ EntityManager
        List<ProductOffering> productOfferings = entityManager.createQuery(query).getResultList();
        return productOfferings;
    }
}
