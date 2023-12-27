package com.semicolonLabs.ecommercetask.controllers.sellerController;


import com.semicolonLabs.ecommercetask.exceptions.SellerException;
import com.semicolonLabs.ecommercetask.services.seller.SellerService;
import com.semicolonLabs.ecommercetask.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ecommercetask/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class FindASellerController {

    private final SellerService sellerService;

    @GetMapping("findSellerByEmailAddress")
    public ResponseEntity<ApiResponse> findSellerByEmailAddress(@RequestParam String emailAddress) throws SellerException {
        return new ResponseEntity<>(sellerService.findSellerByEmailAddress(emailAddress), HttpStatus.OK);
    }
}
