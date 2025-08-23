package com.cfs.Secuity_Demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {

    int bal=1000;
    @GetMapping("/bal")
    public int getBalance()
    {
        return bal;
    }

    @PostMapping("/add")
    public int updateBalance(@RequestParam String AcctNo, @RequestParam int num)
    {
        return bal+num;
    }

    @GetMapping("/contact")
    private String contactUS()
    {
        return "you can contact us at: 9878676786";
    }
}
