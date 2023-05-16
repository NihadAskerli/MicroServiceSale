package com.example.productservice.service;

import com.example.productservice.models.Product;
import com.example.productservice.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    public List<Product> getAllProduct(){
        return productRepo.findAll();
    }
}
