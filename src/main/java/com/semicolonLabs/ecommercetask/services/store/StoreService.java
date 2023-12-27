package com.semicolonLabs.ecommercetask.services.store;

import com.semicolonLabs.ecommercetask.data.models.Store;
import com.semicolonLabs.ecommercetask.exceptions.RegistrationException;
import com.semicolonLabs.ecommercetask.exceptions.StoreException;
import com.semicolonLabs.ecommercetask.utils.ApiResponse;

import java.util.Optional;

public interface StoreService {

    Store save(Store store);

    ApiResponse findStoreByStoreName(String storeName) throws RegistrationException, StoreException;

    Optional<Store> findStoreByName(String storeName);
}
