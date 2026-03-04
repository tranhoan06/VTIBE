package org.example.vtibackend.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.vtibackend.common.StatusEnum;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOfferingResponse implements Serializable {
    private Long id;

    private String name;

    private Long price;

    private String color;

    private StatusEnum status;
}
