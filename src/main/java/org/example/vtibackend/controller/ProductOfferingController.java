package org.example.vtibackend.controller;

import org.example.vtibackend.common.StatusEnum;
import org.example.vtibackend.entity.ProductOffering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.vtibackend.service.ProductOfferingService;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductOfferingController {
    @Autowired
    private ProductOfferingService productOfferingService;

    @GetMapping("/product")
    public ResponseEntity<ProductOffering> getById(@RequestParam Long id) {
        ProductOffering productOffering = productOfferingService.getById(id);
        return ResponseEntity.ok(productOffering);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductOffering>> getAll() {
        return ResponseEntity.ok(productOfferingService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductOffering>> search(
            @RequestParam String name,
            @RequestParam(required = false) String color) {

        return ResponseEntity.ok(productOfferingService.findByNameAndColor(name, color));
    }

    @PostMapping("/addProduct")
    private ResponseEntity<ProductOffering> addProduct(@RequestBody ProductOffering productOffering) {
        if (productOffering.getId() != null) {
            throw new RuntimeException("Khong duoc truyen id vao");
        }
        ProductOffering newProduct = productOfferingService.addProduct(productOffering);
        return ResponseEntity.ok(newProduct);
    }

    @PutMapping("/updateProduct")
    private Optional<ProductOffering> updateProduct(@RequestBody ProductOffering productOffering) {
        return productOfferingService.updateProduct(productOffering);
    }

    @GetMapping("/filter")
    private ResponseEntity<List<ProductOffering>> getFilterOffering(@RequestParam StatusEnum status, @RequestParam Long price) {
        List<ProductOffering> productOfferings = productOfferingService.getFIlter(status, price);
        return ResponseEntity.ok(productOfferings);
    }

    @GetMapping("/getOfferingDetailGreaterThanTwo")
    private ResponseEntity<List<ProductOffering>> getOfferingDetailGreaterThanTwo() {
        List<ProductOffering> productOfferings = productOfferingService.getOfferingDetailGreaterThanTwo();
        return ResponseEntity.ok(productOfferings);
    }
}
