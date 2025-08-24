package com.cfs.SecurityP04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void saveUsers(List<UserRequest> userRequests)
    {
        for(UserRequest req:userRequests)
        {
            AppUser user=new AppUser();
            user.setUsername(req.getUsername());
            user.setPassword(encoder.encode(req.getPassword()));
            user.setEnabled(true);

            Set<Role> roleSet=new HashSet<>();
            for(String roleName:req.getRoles())
            {
                Role role=roleRepository.findByName(roleName)
                        .orElseGet(()->{
                            Role newRole=new Role();
                            newRole.setName(roleName);
                            return roleRepository.save(newRole);
                        });
                roleSet.add(role);
            }
            user.setRoles(roleSet);
            userRepository.save(user);
        }
    }
}
