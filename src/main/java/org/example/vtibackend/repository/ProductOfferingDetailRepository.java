package org.example.vtibackend.repository;

import org.example.vtibackend.entity.ProductOfferingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOfferingDetailRepository extends JpaRepository<ProductOfferingDetail, Long> {
}
