package com.semicolonLabs.ecommercetask.data.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "seller")
@Data
public class Seller {

    @Id
    private String id;
    @Field(name = "firstName", type = FieldType.Text)
    private String firstName;
    @Field(name = "lastName", type = FieldType.Text)
    private String lastName;
    @Field(name = "emailAddress", type = FieldType.Text)
    private String emailAddress;
    @Field(name = "password", type = FieldType.Keyword)
    private String password;

    @Field (name = "store", type = FieldType.Object)
    private Store store;

}
