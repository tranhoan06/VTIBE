package org.example.vtibackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailRequest implements Serializable {
    @NotBlank(message = "Name must not be blank")
    private String weight;

    @NotBlank(message = "Name must not be blank")
    private String feature;

    @NotBlank(message = "Name must not be blank")
    private String power;

    @NotBlank(message = "Name must not be blank")
    private String brand;

    @NotBlank(message = "Name must not be blank")
    private String image;

    private String video;
}
