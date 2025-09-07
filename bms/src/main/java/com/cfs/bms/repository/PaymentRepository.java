package com.cfs.bms.repository;

import com.cfs.bms.model.Movie;
import com.cfs.bms.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long>
{

    Optional<Payment> findByTransactionId(String transactionId);

}
