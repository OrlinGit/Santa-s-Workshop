package com.workshop.santa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/api")
        public Map<String, Object> getApiInfo(){
        Map<String, Object> info = new HashMap<>();
        info.put("app name", "Santa's Workshop");
        info.put("version", "1.0.0");
        info.put("current server time", LocalDateTime.now());
        info.put("resources", List.of("gifts", "elves", "deliveries"));
        return info;
        }
    }

