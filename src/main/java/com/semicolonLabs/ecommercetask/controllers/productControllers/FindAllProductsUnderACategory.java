package com.semicolonLabs.ecommercetask.controllers.productControllers;


import com.semicolonLabs.ecommercetask.data.models.Product;
import com.semicolonLabs.ecommercetask.exceptions.ProductException;
import com.semicolonLabs.ecommercetask.services.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ecommercetask/")
@CrossOrigin(origins = "*")
@AllArgsConstructor

public class FindAllProductsUnderACategory {
    private final ProductService productService;

    @GetMapping("findAllProductUnderProductCategory")
    public ResponseEntity<SearchHits<Product>> findAllProductsUnderCategoryName(@RequestParam String productCategory) throws ProductException {
        return new ResponseEntity<>(productService.FetchAllProductsByProductCategory(productCategory), HttpStatus.OK);
    }
}
