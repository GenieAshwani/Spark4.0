package com.cfs.SecurityP02.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {


    @GetMapping("/contactUs")
    public String contactUS()
    {
        return "Contact US at :9876252662";
    }

    @GetMapping("/transfer")
    public String transfer()
    {
        return "money transfer successful";
    }

    @GetMapping("/admin")
    public String admin()
    {
        return "welcome admin";
    }

    @GetMapping("/about")
    public String about()
    {
        return "Suresh founder of Laxmi Chit Fund";
    }
}
