package br.com.university.inventorycontrolsupermarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ProductDTO {
        private String id;
        private String nameProduct;
        private String brand;
        private LocalDateTime createdAt;
        private int quantity;
        private String price;
}
