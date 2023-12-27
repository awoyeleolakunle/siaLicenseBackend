package com.semicolonLabs.ecommercetask.services.store;


import com.semicolonLabs.ecommercetask.data.models.Seller;
import com.semicolonLabs.ecommercetask.data.models.Store;
import com.semicolonLabs.ecommercetask.dtos.request.StoreCreationRequest;
import com.semicolonLabs.ecommercetask.exceptions.RegistrationException;
import com.semicolonLabs.ecommercetask.exceptions.StoreException;
import com.semicolonLabs.ecommercetask.services.seller.SellerService;
import com.semicolonLabs.ecommercetask.utils.ApiResponse;
import com.semicolonLabs.ecommercetask.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StoreCreationService {

    private final StoreService storeService;
    private final SellerService sellerService;

    public ApiResponse createStore(StoreCreationRequest storeCreationRequest) throws RegistrationException, StoreException {

        Optional<Seller> registeredSeller = sellerService.findByEmail(storeCreationRequest.getSellerEmailAddress());
        if (registeredSeller.isEmpty()) throw new RegistrationException(GenerateApiResponse.INVALID_DETAILS);
        Seller foundSeller = registeredSeller.get();


        Optional<Store> store = storeService.findStoreByName(storeCreationRequest.getStoreName());
        if(store.isPresent()) throw new StoreException(GenerateApiResponse.STORE_ALREADY_EXISTS);

        attachAStoreToSellerAccount(storeCreationRequest, foundSeller);

        return GenerateApiResponse.created(GenerateApiResponse.STORE_SUCCESSFULLY_CREATED);
    }



    private void attachAStoreToSellerAccount(StoreCreationRequest storeCreationRequest, Seller foundSeller) {
        Store store = new Store();
        store.setStoreName(storeCreationRequest.getStoreName());
        Store savedStore = storeService.save(store);
        foundSeller.setStore(savedStore);
        sellerService.saveSeller(foundSeller);
    }
}
