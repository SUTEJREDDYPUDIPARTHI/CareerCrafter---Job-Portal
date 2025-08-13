package com.hexaware.careercrafter.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MeController {

    @GetMapping("/me")
    public String me(Authentication auth) {
        return auth == null ? "anonymous" : auth.getName();
    }
}
