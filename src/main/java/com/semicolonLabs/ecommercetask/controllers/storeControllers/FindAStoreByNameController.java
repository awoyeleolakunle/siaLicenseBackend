package com.semicolonLabs.ecommercetask.controllers.storeControllers;


import com.semicolonLabs.ecommercetask.exceptions.RegistrationException;
import com.semicolonLabs.ecommercetask.exceptions.StoreException;
import com.semicolonLabs.ecommercetask.services.store.StoreService;
import com.semicolonLabs.ecommercetask.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ecommercetask/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class FindAStoreByNameController {
    private final StoreService storeService;

    @GetMapping("findStore")
    public ResponseEntity<ApiResponse> findStoreByName(@RequestParam String storeName) throws RegistrationException, StoreException {
        return new ResponseEntity<>(storeService.findStoreByStoreName(storeName), HttpStatus.OK);
    }
}
