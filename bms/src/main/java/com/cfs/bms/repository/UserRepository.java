package com.cfs.bms.repository;

import com.cfs.bms.model.Theater;
import com.cfs.bms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>
{
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

}
