package com.semicolonLabs.ecommercetask.services.seller;

import com.semicolonLabs.ecommercetask.data.models.Seller;
import com.semicolonLabs.ecommercetask.exceptions.SellerException;
import com.semicolonLabs.ecommercetask.utils.ApiResponse;

import java.util.Optional;

public interface SellerService {
    Seller saveSeller(Seller seller);
    Optional<Seller> findSellerById(String id);

    ApiResponse findSellerByEmailAddress(String emailAddress) throws SellerException;

    Optional<Seller> findByEmail(String emailAddress);

}
