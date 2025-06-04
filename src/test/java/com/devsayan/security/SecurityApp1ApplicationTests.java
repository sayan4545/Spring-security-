package com.devsayan.security;
import com.devsayan.security.entities.User;
import com.devsayan.security.services.JWTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApp1ApplicationTests {
    @Autowired
    JWTService jwtService;
    @Test
    void contextLoads() {
        User user = new User(1L,"sayan@1234","3456","sayan");
        String token = jwtService.generateToken(user);
        System.out.println(token);
        Long id = jwtService.getuserIdFromToken(token);
        System.out.println("extracted id is : "+ id);
    }
}
