package com.chat.socket.ms_chat_socket.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class TestController {

    @GetMapping("/")
    public String test() {
        return "testing backend connection";
    }
}
