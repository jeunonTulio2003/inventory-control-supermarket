package br.com.university.inventorycontrolsupermarket.model;

import br.com.university.inventorycontrolsupermarket.enums.InventoryAreaEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

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
    private String price;
    private InventoryAreaEnum inventoryAreaEnum;
}
