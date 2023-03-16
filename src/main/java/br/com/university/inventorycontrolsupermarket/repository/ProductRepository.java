package br.com.university.inventorycontrolsupermarket.repository;

import br.com.university.inventorycontrolsupermarket.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
