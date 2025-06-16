package com.ecommerce.store.controllers;

import com.ecommerce.store.entities.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagingController {

    @RequestMapping("/say-hello")
    public Message sayHello() {
        return new Message("Hello World");
    }
}
