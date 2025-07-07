package com.lawyer.service;

import com.lawyer.entity.Register;
import com.lawyer.request.LoginRequest;
import response.LoginResponse;

public interface RegisterService {
    boolean registration(Register register);
    LoginResponse login(LoginRequest loginRequest);
}
