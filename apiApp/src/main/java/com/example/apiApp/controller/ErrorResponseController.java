package com.example.apiApp.controller;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Filterクラスで発生した例外をスローするためのコントローラーです。
 */
@RestController
public class ErrorResponseController {

    /**
     * JWTVerificationExceptionをスローします。
     * @return
     */
    @GetMapping("filter_error")
    public String getFilterError(){
        throw new JWTVerificationException(null);
    }

}
