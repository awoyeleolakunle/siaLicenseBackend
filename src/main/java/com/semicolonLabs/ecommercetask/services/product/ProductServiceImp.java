package com.semicolonLabs.ecommercetask.services.product;

import com.semicolonLabs.ecommercetask.data.models.Product;
import com.semicolonLabs.ecommercetask.data.repositories.ProductRepository;
import com.semicolonLabs.ecommercetask.exceptions.ProductException;
import com.semicolonLabs.ecommercetask.utils.ApiResponse;
import com.semicolonLabs.ecommercetask.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService{
    private final ProductRepository productRepository;

    private final ElasticsearchOperations elasticsearchOperations;
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findProductByName(String productName) {
        return productRepository.findByProductName(productName);
    }

    @Override
    public ApiResponse findProductByProductName(String productName) throws ProductException {
        Optional<Product> existingProduct = findProductByName(productName);
        if(existingProduct.isEmpty()) throw new ProductException(GenerateApiResponse.PRODUCT_NOT_FOUND);
        return GenerateApiResponse.found(existingProduct.get());
    }

    @Override
    public ApiResponse findAllProductByCategory(String productCategory) throws ProductException {
        List<Product> listOfProducts = productRepository.findByProductCategory(productCategory.toUpperCase());
        return GenerateApiResponse.found(listOfProducts);
    }


@Override
    public SearchHits<Product> FetchAllProductsByProductCategory(String productCategory) {

//        Query query = (Query) new StringQuery(
//                "{ \"match\": { \"name\": { \"query\": \"" + productCategory + " \" } } } ");
    NativeSearchQuery query = new NativeSearchQueryBuilder()
            .withQuery(QueryBuilders.matchQuery("productCategory", productCategory.toUpperCase()))
            .build();
        return elasticsearchOperations.search(query, Product.class);
    }
}
