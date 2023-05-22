package br.com.university.inventorycontrolsupermarket.service.impl;

import br.com.university.inventorycontrolsupermarket.dto.ProductDTO;
import br.com.university.inventorycontrolsupermarket.enums.InventoryAreaEnum;
import br.com.university.inventorycontrolsupermarket.exception.AreaNotFoundException;
import br.com.university.inventorycontrolsupermarket.model.Product;
import br.com.university.inventorycontrolsupermarket.repository.ProductRepository;
import br.com.university.inventorycontrolsupermarket.service.ProductService;
import org.apache.catalina.core.AprLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static br.com.university.inventorycontrolsupermarket.constantes.Constantes.ALL;
import static br.com.university.inventorycontrolsupermarket.constantes.Constantes.FILTER;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired private ProductRepository productRepository;
    @Override
    public boolean saveProduct(ProductDTO productDTO) {

        InventoryAreaEnum areaEnum = validateArea(productDTO.getArea());

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
    public List<Product> findAllProducts(String filter) {


        List<Product> results = validateFilter(filter);
        return  results;
    }

    private List<Product> validateFilter(String filter){
        if(filter.equalsIgnoreCase(ALL)){
            return productRepository.findAll();
        }
        if(filter.equalsIgnoreCase(FILTER)){
            return productRepository.findProductByQuantityThanZero(0);
        }
        else {
            throw new AreaNotFoundException("Filter invalid");
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

        if(product.isPresent()){
            productUpdate.setCreatedAt(product.get().getCreatedAt());
            productUpdate.setInventoryAreaEnum(product.get().getInventoryAreaEnum());
            productRepository.save(productUpdate);
            return productUpdate;
        }
        return null;
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

    private InventoryAreaEnum validateArea(String area){
        InventoryAreaEnum areaEnum = InventoryAreaEnum.fromDescription(area);

        if(areaEnum == null){
            throw new AreaNotFoundException("Area not found");
        }
        return areaEnum;
    }
}
