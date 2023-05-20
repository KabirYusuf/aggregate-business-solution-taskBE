package com.kyaa.aggregatebusinesssolutiontaskbackend.data.repository;

import com.kyaa.aggregatebusinesssolutiontaskbackend.data.model.BankAccountNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountNumberRepository extends JpaRepository<BankAccountNumber, Long> {
}
