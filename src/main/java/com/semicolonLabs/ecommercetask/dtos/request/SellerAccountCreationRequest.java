package com.semicolonLabs.ecommercetask.dtos.request;

import lombok.Data;

@Data
public class SellerAccountCreationRequest {
    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;
}
