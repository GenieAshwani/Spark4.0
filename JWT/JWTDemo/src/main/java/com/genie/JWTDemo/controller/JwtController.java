package com.genie.JWTDemo.controller;

import com.genie.JWTDemo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class JwtController {


    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/generate-Token")
    public String generateToken(@RequestParam String username ,@RequestParam String pass)
    {
        if("admin".equals(username) && "admin".equals(pass))
        {
            //generate
            return jwtUtil.generateToken(username);
        }
        else{
            return "Invalid credentials";
        }
    }

@GetMapping("/fund")
   public String fundTranfer(@RequestHeader(value="Authorization",required = false) String authorizationHeader)
    {
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer "))
        {
            String token=authorizationHeader.substring(7);

            if(jwtUtil.validateToken(token))
            {
                return "This is secure API , token valid";
            }
            else {
                return "Invalid Token";
            }
        }
        else {
            return "Authorization header is missing ";
        }

    }
}
