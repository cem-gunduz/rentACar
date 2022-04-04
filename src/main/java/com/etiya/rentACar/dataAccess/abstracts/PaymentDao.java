package com.etiya.rentACar.dataAccess.abstracts;


import com.etiya.rentACar.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDao extends JpaRepository<Payment, Integer> {
}
