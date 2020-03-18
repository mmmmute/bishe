package com.v1.learn.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;

@Controller
public class ToSignUpController {
    @GetMapping("/toSign")
    public String toSign(){
        return "sign_up";
    }
}
