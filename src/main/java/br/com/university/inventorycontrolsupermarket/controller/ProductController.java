package br.com.university.inventorycontrolsupermarket.controller;

import br.com.university.inventorycontrolsupermarket.dto.ProductDTO;
import br.com.university.inventorycontrolsupermarket.model.Product;
import br.com.university.inventorycontrolsupermarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(value = "/save")
    public ResponseEntity<String> saveProduct(@RequestBody @Validated ProductDTO productDTO) {
        if (productService.saveProduct(productDTO)) {
            return ResponseEntity.ok().body("Salvo com sucesso");
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Product>> findAllProducts(@RequestParam(required = false, defaultValue = "All")List<Integer> filter) {
        List<Product> productList = productService.findAllProducts(filter);
        if (productList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(productList);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") String id) {
        if (productService.deleteProduct(id)) {
            return ResponseEntity.ok().body("Deletado com sucesso");
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product productUpdate = productService.updateProduct(product);
        if(productUpdate == null){
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok().body(productUpdate);
    }

    @GetMapping(value = "/area/{area}")
    public ResponseEntity<List<Product>> findProductByArea(@PathVariable("area") String area){
        List<Product> list = productService.findProductByArea(area);
        if(list.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/brand/{brand}")
    public ResponseEntity<List<Product>> findProductByBrand(@PathVariable("brand") String brand){
        List<Product> list = productService.findProductByBrand(brand);
        if(list.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }
}