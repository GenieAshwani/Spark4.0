package com.cfs.JPAP02.repo;

import com.cfs.JPAP02.entity.Laptop;
import com.cfs.JPAP02.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepo extends JpaRepository<Laptop,Long> {
}
