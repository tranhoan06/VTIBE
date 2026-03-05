package org.example.vtibackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOfferingDetailRequest implements Serializable {
    @NotNull
    private Long productOfferingId;

    @NotEmpty(message = "productDetailIds must not be empty")
    private List<Long> productDetailIds;
}
