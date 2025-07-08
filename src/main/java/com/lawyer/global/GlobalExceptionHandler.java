package com.lawyer.global;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import response.ApiResponse;

import java.security.SignatureException;
import java.sql.Timestamp;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleAllExceptions(Exception ex) {
        ApiResponse response = new ApiResponse(
                "Server Error: " + ex.getMessage(),
                false,
                new Timestamp(System.currentTimeMillis())
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse> handleUserNotFound(UsernameNotFoundException ex) {
        ApiResponse response = new ApiResponse(
                ex.getMessage(),
                false,
                new Timestamp(System.currentTimeMillis())
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse> handleBadCredentials(BadCredentialsException ex) {
        ApiResponse response = new ApiResponse(
                ex.getMessage(),
                false,
                new Timestamp(System.currentTimeMillis())
        );
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResponse> handleExpiredJwt(ExpiredJwtException ex){
        ApiResponse response=new ApiResponse(
                ex.getMessage(),
                false,
                new Timestamp(System.currentTimeMillis())
        );
        return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({MalformedJwtException.class, SignatureException.class, IllegalArgumentException.class})
    public ResponseEntity<ApiResponse> handleInvalidToken(RuntimeException ex){
        ApiResponse response=new ApiResponse(
                ex.getMessage(),
                false,
                new Timestamp(System.currentTimeMillis())
        );
        return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
    }
}
