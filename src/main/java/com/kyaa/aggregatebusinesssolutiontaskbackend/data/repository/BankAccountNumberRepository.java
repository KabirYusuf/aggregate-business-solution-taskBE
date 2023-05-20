package com.kyaa.aggregatebusinesssolutiontaskbackend.data.repository;

import com.kyaa.aggregatebusinesssolutiontaskbackend.data.model.BankAccountNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountNumberRepository extends JpaRepository<BankAccountNumber, Long> {
    Optional<BankAccountNumber> findBankAccountNumberByBankCodeAndSerialNumber(String bankCode, String serialNumber);
}
