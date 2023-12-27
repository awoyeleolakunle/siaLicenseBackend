package com.semicolonLabs.ecommercetask.services.seller;

import com.semicolonLabs.ecommercetask.data.models.Seller;
import com.semicolonLabs.ecommercetask.data.repositories.SellerRepository;
import com.semicolonLabs.ecommercetask.exceptions.SellerException;
import com.semicolonLabs.ecommercetask.utils.ApiResponse;
import com.semicolonLabs.ecommercetask.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor

public class SellerServiceImp implements SellerService{

    private final SellerRepository sellerRepository;
    @Override
    public Seller saveSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public Optional<Seller> findSellerById(String id) {
        return sellerRepository.findById(id);
    }

    @Override
    public ApiResponse findSellerByEmailAddress(String emailAddress) throws SellerException {
        var foundUser =  sellerRepository.findByEmailAddress(emailAddress);
        if(foundUser.isEmpty())throw new SellerException(GenerateApiResponse.INVALID_DETAILS);
        return GenerateApiResponse.found(foundUser.get());
    }

    @Override
    public Optional<Seller> findByEmail(String emailAddress) {
        return sellerRepository.findByEmailAddress(emailAddress);
    }
}
