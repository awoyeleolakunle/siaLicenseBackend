package com.semicolonLabs.ecommercetask.data.repositories;

import com.semicolonLabs.ecommercetask.data.models.Store;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface StoreRepository extends ElasticsearchRepository<Store, String> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"storeName\": \"?0\"}}]}}")
    Optional<Store> findByStoreName(String storeName);

}
