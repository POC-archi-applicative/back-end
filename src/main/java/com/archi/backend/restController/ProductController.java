package com.archi.backend.restController;

import com.archi.backend.dao.entity.Product;
import com.archi.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api")
public class ProductController {

    public ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("allProducts")
    public List<Product> getAllProducts() {
        return productService.getCatalog();
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable("id") String id) {
        Optional<Product> optProduct = productService.getProductById(id);
        if (optProduct.isPresent()) {
            Product product = optProduct.get();
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody Product productDetails) {
        Product updateProduct = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(updateProduct);
    }

    @PostMapping("product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") String id) {
       Product product =  productService.getProductById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit inexistant pour cet id"));
       if (product.getId() == null){
           return ResponseEntity.notFound().build();
       }
       productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
