package org.example.vtibackend.repository;

import org.example.vtibackend.entity.ProductOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOfferingRepository extends JpaRepository<ProductOffering, Long> {

    List<ProductOffering> findByName(String name);

    List<ProductOffering> findByNameAndColor(String name, String color);
}
