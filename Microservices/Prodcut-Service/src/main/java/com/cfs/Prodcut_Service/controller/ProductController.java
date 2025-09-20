package com.cfs.Prodcut_Service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ProductController {


    @Value("${msg}")
    private String msg;
    @Autowired
    private Environment env;


    @GetMapping("/msg")
    public String msgTest()
    {
        return msg;
    }

    @GetMapping("/place")
    public String placeOrder()
    {
        String port = env.getProperty("server.port");
        return " running on port "+port+" Hello you order : Iphone is placed";
    }

    @GetMapping("/product/{name}")
    public String orderWithName(@PathVariable String name)
    {
        return "Hello "+name+" Your order done";
    }
}
