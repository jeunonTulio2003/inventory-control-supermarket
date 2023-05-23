package br.com.university.inventorycontrolsupermarket.repository;

import br.com.university.inventorycontrolsupermarket.enums.InventoryAreaEnum;
import br.com.university.inventorycontrolsupermarket.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    @Query("{ 'inventoryAreaEnum':  ?0}")
    List<Product> findProduct(InventoryAreaEnum area);
    @Query(value = "{ 'quantity':  { $gte:  ?0, $lte:  ?1 } }")
    List<Product> findProductBetween(Integer min, Integer max);

    List<Product> findProductByBrand(String brand);

    Optional<Product> findByNameProduct(String name);
}
