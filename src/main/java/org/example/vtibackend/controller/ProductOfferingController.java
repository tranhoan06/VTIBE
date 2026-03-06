package org.example.vtibackend.controller;

import jakarta.validation.Valid;
import org.example.vtibackend.common.StatusEnum;
import org.example.vtibackend.dto.request.ProductOfferingRequest;
import org.example.vtibackend.dto.response.ProductOfferingResponse;
import org.example.vtibackend.entity.ProductOffering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ProductOfferingResponse> addProduct(
            @Valid @RequestBody ProductOfferingRequest request) {

        ProductOfferingResponse response = productOfferingService.addProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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

    @GetMapping("/filter")
    public ResponseEntity<List<ProductOffering>> filterProductOffering(@RequestParam(name = "name", required = false) String name,
                                                                       @RequestParam(name = "minPrice", required = false) Long minPrice,
                                                                       @RequestParam(name = "maxPrice", required = false) Long maxPrice,
                                                                       @RequestParam(name = "color", required = false) String color,
                                                                       @RequestParam(name = "status", required = false) StatusEnum status) {
        List<ProductOffering> productOfferingList = productOfferingService.filter(name, minPrice, maxPrice, color, status);
        return ResponseEntity.ok(productOfferingList);
    }
}
