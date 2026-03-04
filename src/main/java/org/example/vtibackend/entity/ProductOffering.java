package org.example.vtibackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.vtibackend.common.StatusEnum;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "product_offering")
@Getter
@Setter
@AllArgsConstructor // constructor co tham so
@NoArgsConstructor // constructor k co tham so
public class ProductOffering implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Long price = 0L;

    @Column(name = "color")
    private String color;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @OneToMany(mappedBy = "productOfferings")
    private List<ProductOfferingDetail> productOfferingDetail;

//    @OneToOne(fetch = FetchType.LAZY)
//    @ManyToOne
//    @JoinColumn(name = "detail_id", referencedColumnName = "id")
//    private ProductDetail productDetail;

    @Override
    public String toString() {
        return "ProductOffering{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                '}';
    }
}
