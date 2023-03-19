package br.com.university.inventorycontrolsupermarket.service.impl;

import br.com.university.inventorycontrolsupermarket.dto.ProductDTO;
import br.com.university.inventorycontrolsupermarket.model.Product;
import br.com.university.inventorycontrolsupermarket.repository.ProductRepository;
import br.com.university.inventorycontrolsupermarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
                        .price(productDTO.getPrice())
                        .build();

        productRepository.save(product);
        return true;
    }
    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
    @Override
    public boolean deleteProduct(String id) {
        Optional<Product> product = productRepository.findById(id);

        //Optional.ofNullable(productRepository.findById(id)).orElseThrow(() -> new RuntimeException());

        if(product.isPresent()){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Product updateProduct(Product productUpdate) {
        Optional<Product> product = productRepository.findById(productUpdate.getId());

        if(product.isPresent()){
            productRepository.save(productUpdate);
            return productUpdate;
        }
        return null;
    }
}
