package com.oshan.snack.sprint.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("")
    public ResponseEntity<String> Homecontroller(){
        return new ResponseEntity<>("Welocme snack sprint", HttpStatus.OK);
    }
}
