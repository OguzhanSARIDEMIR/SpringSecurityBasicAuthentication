package com.works.services;

import com.works.entities.Role;
import com.works.entities.User;
import com.works.repositories.RoleRepository;
import com.works.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserServices implements UserDetailsService {


    final UserRepository uRepo;
    final RoleRepository rRepo;
    final PasswordEncoder encoder;
    public UserServices(UserRepository uRepo, RoleRepository rRepo, @Lazy PasswordEncoder encoder) {
        this.uRepo = uRepo;
        this.rRepo = rRepo;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        System.out.println( username );
        Optional<User> optionalUser = uRepo.findByEmailEqualsIgnoreCase( username );
        if ( optionalUser.isPresent() ) {
            User vtUser = optionalUser.get();
            System.out.println( vtUser );
            userDetails = new org.springframework.security.core.userdetails.User(
                    username,
                    vtUser.getPassword(),
                    vtUser.isEnabled(),
                    vtUser.isTokenExpired(),
                    true,
                    true,
                    getAuthorities(vtUser.getRoles())
            );
        }else {
            throw new UsernameNotFoundException("Böyle bir kullanıcı yok");
        }
        return userDetails;
    }

    private List<GrantedAuthority> getAuthorities (List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority( role.getName() ));
        }
        return authorities;
    }

    public ResponseEntity register(User user ) {
        Optional<User> oUser = uRepo.findByEmailEqualsIgnoreCase(user.getEmail());
        Map<String,Object> hm =new LinkedHashMap<>();

        if (oUser.isPresent() ) {
            hm.put("status", false);
            hm.put("result", user  );
            hm.put("message", "Bu kullanıcı daha önce kayıtlı!");
            return new ResponseEntity( hm, HttpStatus.BAD_REQUEST );
        }else {
            user.setPassword(encoder.encode(user.getPassword()));
            hm.put("status", true);
            hm.put("result",  uRepo.save( user ) );
            return new ResponseEntity( hm, HttpStatus.OK );
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
