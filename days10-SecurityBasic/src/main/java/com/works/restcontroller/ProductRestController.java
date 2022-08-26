package com.works.restcontroller;

import com.works.entities.Product;
import com.works.services.ProductServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductRestController {
    final ProductServices pServices;

    public ProductRestController(ProductServices pServices) {
        this.pServices = pServices;
    }
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Product product){
        return pServices.save(product);
    }
    @GetMapping("/list")
    public ResponseEntity list(){
        return pServices.list();
    }
}
