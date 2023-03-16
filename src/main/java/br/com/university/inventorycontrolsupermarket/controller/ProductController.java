package br.com.university.inventorycontrolsupermarket.controller;

import br.com.university.inventorycontrolsupermarket.dto.ProductDTO;
import br.com.university.inventorycontrolsupermarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired private ProductService productService;
    @PostMapping(value = "/save")
    public ResponseEntity<String> saveProduct(@RequestBody ProductDTO productDTO){
        if(productService.saveProduct(productDTO)){
            return ResponseEntity.ok().body("Salvo com sucesso");
        }
        return ResponseEntity.unprocessableEntity().build();
    }
}
