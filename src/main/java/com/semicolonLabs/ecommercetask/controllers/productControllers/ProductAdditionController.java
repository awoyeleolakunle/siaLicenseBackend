package com.semicolonLabs.ecommercetask.controllers.productControllers;


import com.semicolonLabs.ecommercetask.dtos.request.ProductAdditionRequest;
import com.semicolonLabs.ecommercetask.exceptions.ProductException;
import com.semicolonLabs.ecommercetask.exceptions.SellerException;
import com.semicolonLabs.ecommercetask.exceptions.StoreException;
import com.semicolonLabs.ecommercetask.services.product.ProductAdditionService;
import com.semicolonLabs.ecommercetask.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ecommercetask/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ProductAdditionController {

    private final ProductAdditionService productAdditionService;

    @PostMapping("addProduct")
    public ResponseEntity<ApiResponse> addProductToSellerStore(@RequestBody ProductAdditionRequest productAdditionRequest) throws SellerException, ProductException, StoreException {
        return new ResponseEntity<>(productAdditionService.addProductToStore(productAdditionRequest), HttpStatus.CREATED);
    }
}
