package com.semicolonLabs.ecommercetask.data.repositories;


import com.semicolonLabs.ecommercetask.data.models.Seller;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface SellerRepository extends ElasticsearchRepository<Seller, String> {
//    @Query("{\"bool\": {\"must\": [{\"term\": {\"emailAddress.keyword\": \"?0\"}}]}}")
//    Optional<Seller> findByEmailAddress(String emailAddress);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"emailAddress\": \"?0\"}}]}}")
    Optional<Seller> findByEmailAddress(String emailAddress);


    @Query("{\"bool\" :  {\"must\" : [{\"term\" : {\"firstName.keyword\" : \"?0\"}}]}}")
    Optional<Seller> findFirstBy(String firstName);
}


