package org.example.vtibackend.repository;

import org.example.vtibackend.common.StatusEnum;
import org.example.vtibackend.entity.ProductOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOfferingRepository extends JpaRepository<ProductOffering, Long> {

    List<ProductOffering> findByName(String name);

    List<ProductOffering> findByNameAndColor(String name, String color);

    @Query("select po from ProductOffering po " + "where po.status = :status and po.price < :price")
    List<ProductOffering> getFilter(@Param("status") StatusEnum status, @Param("price") Long price);

    @Query("select po from ProductOffering po " + "join ProductOfferingDetail pod on po.id = pod.productOfferings.id group by po having count(pod.productDetail) > 2")
    List<ProductOffering> getOfferingDetailGreaterThanTwo();
}
