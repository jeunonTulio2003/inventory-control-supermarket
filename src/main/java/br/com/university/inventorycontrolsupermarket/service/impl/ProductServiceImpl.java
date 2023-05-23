package br.com.university.inventorycontrolsupermarket.service.impl;

import br.com.university.inventorycontrolsupermarket.dto.ProductDTO;
import br.com.university.inventorycontrolsupermarket.enums.InventoryAreaEnum;
import br.com.university.inventorycontrolsupermarket.exception.UnprocessableEntityException;
import br.com.university.inventorycontrolsupermarket.model.Product;
import br.com.university.inventorycontrolsupermarket.repository.ProductRepository;
import br.com.university.inventorycontrolsupermarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static br.com.university.inventorycontrolsupermarket.constantes.Constantes.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired private ProductRepository productRepository;
    @Override
    public boolean saveProduct(ProductDTO productDTO) {

        InventoryAreaEnum areaEnum = validateArea(productDTO.getArea());

        validateQuantity(productDTO.getQuantity());

        validateProduct(productDTO.getNameProduct());

        Product product =
                Product.builder()
                        .nameProduct(productDTO.getNameProduct())
                        .brand(productDTO.getBrand())
                        .createdAt(LocalDateTime.now())
                        .quantity(productDTO.getQuantity())
                        .price(productDTO.getPrice())
                        .inventoryAreaEnum(areaEnum)
                        .build();

        productRepository.save(product);
        return true;
    }
    @Override
    public List<Product> findAllProducts(List<Integer> filter) {

        return productRepository.findProductBetween(filter.get(0), filter.get(1));
    }

    private void validateProduct(String name) {
        Optional<Product> product = productRepository.findByNameProduct(name);

        if(product.isPresent()){
            throw new UnprocessableEntityException("Product already exists");
        }
    }
    @Override
    public boolean deleteProduct(String id) {
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Product updateProduct(Product productUpdate) {
        Optional<Product> product = productRepository.findById(productUpdate.getId());

        if(!product.isPresent() || product.isEmpty()){
            throw new UnprocessableEntityException("Cannot possible find product");
        }

        if(!product.get().getNameProduct().equalsIgnoreCase(productUpdate.getNameProduct())){

            validateProduct(productUpdate.getNameProduct());

        }

        validateQuantity(productUpdate.getQuantity());

        productUpdate.setCreatedAt(product.get().getCreatedAt());
        productUpdate.setInventoryAreaEnum(product.get().getInventoryAreaEnum());

        productRepository.save(productUpdate);

        return productUpdate;
    }

    @Override
    public List<Product> findProductByArea(String area) {
        InventoryAreaEnum areaEnum = validateArea(area);

        List<Product> list = productRepository.findProduct(areaEnum);

        return list;
    }

    @Override
    public List<Product> findProductByBrand(String brand) {
        List<Product> list = productRepository.findProductByBrand(brand);

        return list;
    }

    private static void validateQuantity(int quantity){
        if(quantity > MAX_QUANTITY){
            throw new UnprocessableEntityException("Quantity cannot be greater than 50");
        }

    }

    private static InventoryAreaEnum validateArea(String area){
        InventoryAreaEnum areaEnum = InventoryAreaEnum.fromDescription(area);

        if(areaEnum == null){
            throw new UnprocessableEntityException("Area not found");
        }
        return areaEnum;
    }
}
