package com.semicolonLabs.ecommercetask.services.store;


import com.semicolonLabs.ecommercetask.data.models.Store;
import com.semicolonLabs.ecommercetask.data.repositories.StoreRepository;
import com.semicolonLabs.ecommercetask.exceptions.RegistrationException;
import com.semicolonLabs.ecommercetask.exceptions.StoreException;
import com.semicolonLabs.ecommercetask.utils.ApiResponse;
import com.semicolonLabs.ecommercetask.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StoreServiceImp  implements StoreService{

    private final StoreRepository storeRepository;
    @Override
    public Store save(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public ApiResponse findStoreByStoreName(String storeName) throws RegistrationException, StoreException {
        Optional<Store> existingStore = findStoreByName(storeName);
        if(existingStore.isEmpty()) throw new StoreException(GenerateApiResponse.STORE_NOT_FOUND);
        return GenerateApiResponse.found(existingStore.get());
    }

    @Override
    public Optional<Store> findStoreByName(String storeName) {
        return storeRepository.findByStoreName(storeName);
    }
}
