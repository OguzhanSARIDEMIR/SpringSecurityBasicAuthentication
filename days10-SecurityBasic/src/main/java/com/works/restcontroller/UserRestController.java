package com.works.restcontroller;

import com.works.entities.User;
import com.works.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")
public class UserRestController {
    final UserServices uServices;

    public UserRestController(UserServices uServices) {
        this.uServices = uServices;
    }

    @PostMapping("/register")
    private ResponseEntity register(@RequestBody User user){
        return uServices.register(user);
    }
}
