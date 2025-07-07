package com.lawyer.ServiceImp;


import com.lawyer.confirgreation.JWTService;
import com.lawyer.domain.USER_ROLE;
import com.lawyer.request.LoginRequest;
import com.lawyer.service.RegisterService;
import com.lawyer.entity.Register;
import com.lawyer.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import response.LoginResponse;

@Service
@RequiredArgsConstructor
public class RegisterServiceImp implements RegisterService {

    private final RegisterRepository registerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public boolean registration(Register register) {
        boolean existRegister=registerRepository.existsByEmail(register.getEmail());
        if(existRegister){
            return false;
        }
        System.out.println(USER_ROLE.USER);
        System.out.println("Test");
        register.setRole(USER_ROLE.USER);
        register.setPassword(bCryptPasswordEncoder.encode(register.getPassword()));
        registerRepository.save(register);
        return true;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Register register = registerRepository.findByEmail(loginRequest.getEmail());
        if (register == null) {
            throw new UsernameNotFoundException("User not found with email: " + loginRequest.getEmail());
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), register.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setRegister(register);
        loginResponse.setToken(jwtService.generateJWTToken(register.getEmail()));
        return loginResponse;
    }
}
