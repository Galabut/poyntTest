package com.poynt.test;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")

public class TestController {

    @PostMapping
    public ResponseEntity<String> getResponseFromPoynt(RequestEntity req) {
        System.out.println("Req received" + req.toString());
        return ResponseEntity.ok("");
    }


}
