package br.com.university.inventorycontrolsupermarket.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
        private String id;
        private String nameProduct;
        private String brand;
        private LocalDateTime createdAt;
        private int quantity;
        private String price;
        private String area;
}
