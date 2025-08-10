package com.cfs.JPAP02.repo;

import com.cfs.JPAP02.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Long> {
}
