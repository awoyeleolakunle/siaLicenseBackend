package com.semicolonLabs.ecommercetask.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductAdditionRequest {
    private String sellerEmailAddress;
    private String productName;
    private String productCategory;
    private Double productPrice;
}
