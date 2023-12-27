package com.semicolonLabs.ecommercetask.data.repositories;

import com.semicolonLabs.ecommercetask.data.models.Product;
import com.semicolonLabs.ecommercetask.data.models.enums.ProductCategory;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"productName\": \"?0\"}}]}}")
    Optional<Product> findByProductName(String productName);

//    @Query("{\"bool\": {\"must\": [{\"match\": {\"productCategory\": \"?0\"}}]}}")
//    List<Product> findByProductCategory(String productCategory);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"productCategory\": \"?0\"}}]}}")
    List<Product> findByProductCategory(String productCategory);

}
