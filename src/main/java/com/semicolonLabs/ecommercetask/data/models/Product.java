package com.semicolonLabs.ecommercetask.data.models;


import com.semicolonLabs.ecommercetask.data.models.enums.ProductCategory;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

@Document(indexName = "product")
@Data
public class Product {
    @Id
    private String id;
    @Field(name = "productName", type = FieldType.Text)
    private String productName;
    @Field(name = "price", type = FieldType.Double)
    private BigDecimal price;
    @Field(value = "productCategory", type = FieldType.Keyword)
    private String productCategory;
}
