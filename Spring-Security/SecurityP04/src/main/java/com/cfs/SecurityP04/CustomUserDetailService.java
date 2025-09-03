package com.cfs.SecurityP04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername method called....");
        AppUser user=userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found "+username));
        System.out.println("Found username = "+user.getUsername() + " / " +user.getPassword());

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),user.isEnabled(),true,true,true,
                //user.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getName())).toList()
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" +role.getName())).toList());

    }
}
