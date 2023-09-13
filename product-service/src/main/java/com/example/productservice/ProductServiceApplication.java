package com.example.productservice;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
@RequiredArgsConstructor
public class ProductServiceApplication implements CommandLineRunner {
private final ProductService productService;
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        productService.createProduct(new ProductRequest("random","test", BigDecimal.valueOf(10)));
    }
}
