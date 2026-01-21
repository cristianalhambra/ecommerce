package com.tienda.ecommerce.controller;

import com.tienda.ecommerce.model.Product;
import com.tienda.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products") // La URL base para todos los endpoints de productos
@CrossOrigin(origins = "http://localhost:4200") // Permite Angular
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // GET - Listar todos
    @GetMapping public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // GET - Obtener por ID
    @GetMapping("/{id}") public Optional<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id);
    }

    // POST - Crear producto
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // PUT - Actualizar producto
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    // DELETE - Eliminar producto
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}