package org.example.vtibackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponse {
    private Long id;

    private String weight;
    private String feature;
    private String power;
    private String brand;
    private String image;
    private String video;
}
