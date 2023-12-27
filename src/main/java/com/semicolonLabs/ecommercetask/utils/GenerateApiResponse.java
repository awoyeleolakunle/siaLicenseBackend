package com.semicolonLabs.ecommercetask.utils;

import com.semicolonLabs.ecommercetask.data.models.Seller;
import org.springframework.http.HttpStatus;

public class GenerateApiResponse {

    public static final String  ACCOUNT_ALREADY_EXIST = "Account with these information already Exists";
    public static final String ACCOUNT_CREATED_SUCCESSFULLY = "You have successfully created an account";
    public static final String INVALID_DETAILS ="Invalid details" ;
    public static final String STORE_SUCCESSFULLY_CREATED ="You have successfully created a store to your seller's account" ;
    public static final String STORE_NOT_FOUND = "No store with such name " ;
    public static final String  STORE_ALREADY_EXISTS = "Store with given name already exists";
    public static final String PRODUCT_NOT_FOUND = "No product with such name" ;
    public static final String PRODUCT_ADDED_SUCCESSFULLY = "You have successfully created and added a product to your store";
    public static final String PRODUCT_ALREADY_ADDED = "You have previously added this product";
    public static final String NO_PRODUCT_UNDER_CATEGORY ="No product under the chosen category";

    public static ApiResponse created(Object data){
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .isSuccessful(true)
                .build();
    }

    public static ApiResponse found(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
    }
}
