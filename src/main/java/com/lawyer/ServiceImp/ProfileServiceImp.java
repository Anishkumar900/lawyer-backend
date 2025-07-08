package com.lawyer.ServiceImp;

import com.lawyer.confirgreation.JWTService;
import com.lawyer.entity.Register;
import com.lawyer.repository.RegisterRepository;
import com.lawyer.service.ProfileService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProfileServiceImp implements ProfileService {

    private final JWTService jwtService;
    private final RegisterRepository registerRepository;

    @Override
    public Register profile(String token) {
//        System.out.println("imp test");
        System.out.println("Extracted username: " + jwtService.extractUsername(token));
        System.out.println("Token expired: " + jwtService.isTokenExpired(token));
        System.out.println("Is token valid: " + jwtService.isTokenValid(token));

//        System.out.println("Token received: " + token);


//        if (!jwtService.isTokenExpired(token)) {
//            throw new ExpiredJwtException(null, null, "JWT token has expired!");
//        }
//        System.out.println("imp test1");
//        if (!jwtService.isTokenValid(token)) {
//            throw new RuntimeException("Invalid JWT token!");
//        }
//        System.out.println("imp test2");
        String username = jwtService.extractUsername(token);
        if (username == null) {
            throw new UsernameNotFoundException("Invalid token: username not found!");
        }
        System.out.println("imp test3");
        Register register = registerRepository.findByEmail(username);
        if (register == null) {
            throw new UsernameNotFoundException("User not found for extracted token username!");
        }


        return register;
    }

}
