package org.example.vtibackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "product_offering")
@Getter
@Setter
@AllArgsConstructor // constructor co tham so
@NoArgsConstructor // constructor k co tham so
public class ProductOffering implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Long price = 0L;

    @Column(name = "color")
    private String color;

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
