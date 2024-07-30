package com.archi.backend.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Catalog")
public class Product {

    @Id
    private String id;
    private String name;
    private double price;
    private String category;
    private String origin;
    private String description;
    private String color;
    private String supplier;
    private int stock;

}
