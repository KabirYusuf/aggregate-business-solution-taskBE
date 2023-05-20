package com.kyaa.aggregatebusinesssolutiontaskbackend.service.implementation;

import com.kyaa.aggregatebusinesssolutiontaskbackend.data.dto.request.GenerateAccountNumberRequest;
import com.kyaa.aggregatebusinesssolutiontaskbackend.exception.ApiResponse;
import com.kyaa.aggregatebusinesssolutiontaskbackend.service.BankAccountNumberService;
import org.springframework.stereotype.Service;

@Service
public class NigerianBankAccountNumberService implements BankAccountNumberService {
    @Override
    public ApiResponse generateAccountNumber(GenerateAccountNumberRequest generateAccountNumberRequest) {
        return null;
    }
}
