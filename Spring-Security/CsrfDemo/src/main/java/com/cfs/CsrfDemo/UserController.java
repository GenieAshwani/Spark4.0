package com.cfs.CsrfDemo;


import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    private Map<String,String> userData=new HashMap<>();

    @GetMapping("/public")
    public String publicMethod()
    {
        return "This is public method";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody Map<String,String> data)
    {
        userData.put(data.get("username"),userData.get("password"));
        return "user saved in memory";
    }

}
