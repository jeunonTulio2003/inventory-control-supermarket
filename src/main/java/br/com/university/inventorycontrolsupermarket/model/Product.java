package br.com.university.inventorycontrolsupermarket.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "Product")
public class Product {

    @Id
    private String id;
    private String nameProduct;
    private String brand;
    private LocalDateTime createdAt;
    private int quantity;
}
