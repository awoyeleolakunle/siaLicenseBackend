package com.semicolonLabs.ecommercetask.controllers.authenticationControllers;


import com.semicolonLabs.ecommercetask.dtos.request.SellerAccountCreationRequest;
import com.semicolonLabs.ecommercetask.exceptions.RegistrationException;
import com.semicolonLabs.ecommercetask.exceptions.SellerException;
import com.semicolonLabs.ecommercetask.services.seller.CreateSellerService;
import com.semicolonLabs.ecommercetask.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ecommercetask/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class SellerRegistrationController {
    private final CreateSellerService createSellerService;

    @PostMapping("register")
    public ResponseEntity<ApiResponse> registerSeller(@RequestBody SellerAccountCreationRequest sellerAccountCreationRequest) throws RegistrationException {
        return new ResponseEntity<>(createSellerService.createSellerAccount(sellerAccountCreationRequest), HttpStatus.CREATED);
    }
}
