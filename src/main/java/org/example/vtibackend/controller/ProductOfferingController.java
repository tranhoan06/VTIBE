package org.example.vtibackend.controller;

import org.example.vtibackend.entity.ProductOffering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.example.vtibackend.service.ProductOfferingService;

import java.util.List;

@RestController
public class ProductOfferingController {
    @Autowired
    private ProductOfferingService productOfferingService;

    @GetMapping("/product")
    public ResponseEntity<ProductOffering> getById(@RequestParam Long id){
        ProductOffering productOffering = productOfferingService.getById(id);
        System.out.println(productOffering);
        return ResponseEntity.ok(productOffering);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductOffering>> getAll(){
        return ResponseEntity.ok(productOfferingService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductOffering>> search(
            @RequestParam String name,
            @RequestParam(required = false) String color) {

        return ResponseEntity.ok(productOfferingService.findByNameAndColor(name, color));
    }
}
