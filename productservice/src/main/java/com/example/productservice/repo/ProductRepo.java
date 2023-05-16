package com.example.productservice.repo;

import com.example.productservice.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product,Long> {
}
