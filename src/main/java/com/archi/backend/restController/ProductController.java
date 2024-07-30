package com.archi.backend.restController;

import com.archi.backend.dao.entity.Product;
import com.archi.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    public ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/allProducts")
    public List<Product> getAllProducts() {
        return productService.getCatalog();
    }

    @GetMapping("/product/{id}")
    public Product getProductByID(@PathVariable String id) {
        Optional<Product> optProduct = this.productService.getProductById(id);
        return optProduct.orElse(null);
    }


}
