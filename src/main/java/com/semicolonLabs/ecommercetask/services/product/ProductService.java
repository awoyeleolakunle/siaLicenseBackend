package com.semicolonLabs.ecommercetask.services.product;

import com.semicolonLabs.ecommercetask.data.models.Product;
import com.semicolonLabs.ecommercetask.exceptions.ProductException;
import com.semicolonLabs.ecommercetask.utils.ApiResponse;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.Optional;

public interface ProductService {
    
    Product save(Product product);
    
    Optional<Product> findProductByName(String productName);
    
    ApiResponse findProductByProductName(String productName) throws ProductException;

    ApiResponse findAllProductByCategory(String productCategory) throws ProductException;

    SearchHits<Product> FetchAllProductsByProductCategory(String productCategory);
}
