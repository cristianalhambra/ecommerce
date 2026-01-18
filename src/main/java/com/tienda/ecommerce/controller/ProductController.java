package com.tienda.ecommerce.controller;

import com.tienda.ecommerce.model.Product;
import com.tienda.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products") // La URL base para todos los endpoints de productos
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // Endpoint: GET /api/products
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Aquí vendrían los endpoints para GET por ID, POST, PUT, DELETE
}

