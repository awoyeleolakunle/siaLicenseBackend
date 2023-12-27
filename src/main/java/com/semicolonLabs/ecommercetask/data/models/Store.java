package com.semicolonLabs.ecommercetask.data.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Set;

@Document(indexName ="store")
@Data
public class Store {
    @Id
    private String id;
    @Field(name = "storeName", type = FieldType.Text)
    private String storeName;
    @Field(name = "products", type = FieldType.Nested, includeInParent = true)
    private Set<Product> setOfProducts;
}
