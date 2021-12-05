package com.example.food.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/hostcheck")
public class HostCheckController {

    @GetMapping
    public ResponseEntity<String> getHostCheck() throws UnknownHostException {
        return new ResponseEntity<>(InetAddress.getLocalHost().getHostAddress()
                + "---" + InetAddress.getLocalHost().getHostName(), HttpStatus.OK);
    }

}
