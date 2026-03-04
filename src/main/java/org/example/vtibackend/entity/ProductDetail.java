package org.example.vtibackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "product_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String weight;
    private String feature;
    private String power;
    private String brand;
    private String image;
    private String video;

    @OneToMany(mappedBy = "productDetail")
    @JsonIgnore
    private List<ProductOfferingDetail> productOfferingDetail;

//    @OneToMany(mappedBy = "productDetail")
//    private List<ProductOffering> productOfferings;


}
