package com.works.restcontroller;

import com.works.entities.Current;
import com.works.entities.Product;
import com.works.services.CurrentServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/current")
public class CurrentRestController {
    final CurrentServices cServices;

    public CurrentRestController(CurrentServices cServices) {
        this.cServices = cServices;
    }
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Current current){
        return cServices.save(current);
    }
    @GetMapping("/list")
    public ResponseEntity list(){
        return cServices.list();
    }
}