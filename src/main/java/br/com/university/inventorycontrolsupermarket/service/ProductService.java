package br.com.university.inventorycontrolsupermarket.service;

import br.com.university.inventorycontrolsupermarket.dto.ProductDTO;
import br.com.university.inventorycontrolsupermarket.model.Product;

import java.util.List;

public interface ProductService {

    boolean saveProduct(ProductDTO productDTO);
    List<Product> findAllProducts();

    boolean deleteProduct(String id);

    Product updateProduct(Product product);

    List<Product> findProductByArea(String area);


}
