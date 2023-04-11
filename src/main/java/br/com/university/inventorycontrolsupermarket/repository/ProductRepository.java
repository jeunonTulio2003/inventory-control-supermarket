package br.com.university.inventorycontrolsupermarket.repository;

import br.com.university.inventorycontrolsupermarket.enums.InventoryAreaEnum;
import br.com.university.inventorycontrolsupermarket.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    @Query("{ 'inventoryAreaEnum':  ?0}")
    List<Product> findProduct(InventoryAreaEnum area);

    List<Product> findProductByBrand(String brand);
}
