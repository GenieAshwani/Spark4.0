package com.cfs.bms.repository;

import com.cfs.bms.model.ShowSeat;
import com.cfs.bms.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater,Long>
{
    List<Theater> findByCity(String city);

}
