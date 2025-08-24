package com.cfs.SecurityP04;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser,String> {

    Optional<AppUser> findByUsername(String name);
}
