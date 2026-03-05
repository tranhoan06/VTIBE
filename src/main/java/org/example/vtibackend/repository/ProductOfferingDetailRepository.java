package org.example.vtibackend.repository;

import org.example.vtibackend.dto.request.ProductOfferingDetailRequest;
import org.example.vtibackend.entity.ProductOffering;
import org.example.vtibackend.entity.ProductOfferingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOfferingDetailRepository extends JpaRepository<ProductOfferingDetail, Long> {
    @Modifying
//    @Modifying trong Spring Data JPA dùng để đánh dấu rằng query sẽ thay đổi dữ liệu
//    trong database (không phải SELECT).
    @Query("delete from ProductOfferingDetail p where p.productOfferings = :productOffering")
    void deleteByProductOfferings(ProductOffering productOffering);

//    void deleteByProductOfferings(ProductOffering productOffering);
}
