package com.kyaa.aggregatebusinesssolutiontaskbackend.service;

import com.kyaa.aggregatebusinesssolutiontaskbackend.data.dto.request.GenerateAccountNumberRequest;
import com.kyaa.aggregatebusinesssolutiontaskbackend.exception.ApiResponse;

public interface BankAccountNumberService {
    ApiResponse generateAccountNumber(GenerateAccountNumberRequest generateAccountNumberRequest);
}
