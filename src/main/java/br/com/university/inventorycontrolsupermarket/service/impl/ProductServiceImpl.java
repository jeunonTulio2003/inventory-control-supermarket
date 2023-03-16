package br.com.university.inventorycontrolsupermarket.service.impl;

import br.com.university.inventorycontrolsupermarket.dto.ProductDTO;
import br.com.university.inventorycontrolsupermarket.model.Product;
import br.com.university.inventorycontrolsupermarket.repository.ProductRepository;
import br.com.university.inventorycontrolsupermarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired private ProductRepository productRepository;
    @Override
    public boolean saveProduct(ProductDTO productDTO) {

        Product product =
                Product.builder()
                        .nameProduct(productDTO.getNameProduct())
                        .brand(productDTO.getBrand())
                        .createdAt(LocalDateTime.now())
                        .quantity(productDTO.getQuantity())
                        .build();

        productRepository.save(product);
        return true;
    }
}
