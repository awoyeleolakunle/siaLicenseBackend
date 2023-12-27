package com.semicolonLabs.ecommercetask.controllers.storeControllers;


import com.semicolonLabs.ecommercetask.dtos.request.StoreCreationRequest;
import com.semicolonLabs.ecommercetask.exceptions.RegistrationException;
import com.semicolonLabs.ecommercetask.exceptions.StoreException;
import com.semicolonLabs.ecommercetask.services.store.StoreCreationService;
import com.semicolonLabs.ecommercetask.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ecommercetask/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class StoreCreationController {
    private final StoreCreationService storeCreationService;

    @PostMapping("createStore")
    public ResponseEntity<ApiResponse> createStore(@RequestBody StoreCreationRequest storeCreationRequest) throws RegistrationException, StoreException {
        return new ResponseEntity<>(storeCreationService.createStore(storeCreationRequest), HttpStatus.CREATED);
    }
}
