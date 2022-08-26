package com.works.services;

import com.works.entities.Current;
import com.works.entities.Product;
import com.works.repositories.CurrentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
@Service
public class CurrentServices {
    final CurrentRepository cRepo;

    public CurrentServices(CurrentRepository cRepo) {
        this.cRepo = cRepo;
    }
    public ResponseEntity save(Current current){
        Map<String ,Object> hm= new LinkedHashMap<>();
        cRepo.save(current);
        hm.put("Status",true);
        hm.put("result",current);


        return new ResponseEntity(hm, HttpStatus.OK);
    }
    public ResponseEntity list(){
        Map<String ,Object> hm=new LinkedHashMap<>();
        hm.put("status",true);
        hm.put("result",cRepo.findAll());
        return new ResponseEntity(hm,HttpStatus.OK);
    }
}