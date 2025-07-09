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


        if (jwtService.isTokenExpired(token)) {
            throw new ExpiredJwtException(null, null, "JWT token has expired!");
        }
        if (!jwtService.isTokenValid(token)) {
            throw new RuntimeException("Invalid JWT token!");
        }
        String username = jwtService.extractUsername(token);
        if (username == null) {
            throw new UsernameNotFoundException("Invalid token: username not found!");
        }
        Register register = registerRepository.findByEmail(username);
        if (register == null) {
            throw new UsernameNotFoundException("User not found for extracted token username!");
        }

        return register;
    }

}
