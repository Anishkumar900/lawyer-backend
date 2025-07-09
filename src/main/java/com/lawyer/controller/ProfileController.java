package com.lawyer.controller;


import com.lawyer.entity.Register;
import com.lawyer.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/profile")
    public ResponseEntity<?> profile(@RequestHeader("Authorization") String token){
//        System.out.println("controller test");
         token = token.replace("Bearer ", "");
        Register register = profileService.profile(token);
        return ResponseEntity.ok(register);
    }

}
