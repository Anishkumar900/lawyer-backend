package com.lawyer.controller;


import com.lawyer.cloudeService.CloudinaryService;
import com.lawyer.request.LoginRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.lawyer.response.ApiResponse;
import com.lawyer.service.RegisterService;
import com.lawyer.entity.Register;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.lawyer.response.LoginResponse;

import java.io.IOException;
import java.sql.Timestamp;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegisterService registerService;
    private final CloudinaryService cloudinaryService;


    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestPart("create") Register register,
            @RequestPart(value = "profilePhoto", required = false) MultipartFile profilePhoto
    ) {

        try{
            if(profilePhoto!=null){
                String imageURL= cloudinaryService.fileUpload(profilePhoto);
                register.setProfilePhoto(imageURL);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boolean registered = registerService.registration(register);

        if (!registered) {
            return new ResponseEntity<>(new ApiResponse("Email already exists", false,new Timestamp(System.currentTimeMillis())), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ApiResponse("User registered successfully", true,new Timestamp(System.currentTimeMillis())), HttpStatus.CREATED);

    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = registerService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

}
