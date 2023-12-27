package com.semicolonLabs.ecommercetask.controllers.productControllers;


import com.semicolonLabs.ecommercetask.exceptions.ProductException;
import com.semicolonLabs.ecommercetask.services.product.ProductService;
import com.semicolonLabs.ecommercetask.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ecommercetask/")
@CrossOrigin(origins = "*")
@AllArgsConstructor

public class FIndAProductByNameController {
    private final ProductService productService;

    @GetMapping("findProductByName")
    public ResponseEntity<ApiResponse> findProductByProductName(@RequestParam String productName) throws ProductException {
        return new ResponseEntity<>(productService.findProductByProductName(productName), HttpStatus.OK);
    }
}
