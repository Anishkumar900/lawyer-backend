package com.lawyer.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

//    @GetMapping
//    public String test(){
//        return "test";
//    }
    @GetMapping
    public ResponseEntity<?> aniket(){
        return new ResponseEntity<>("Aniket",HttpStatus.OK);
    }
}
