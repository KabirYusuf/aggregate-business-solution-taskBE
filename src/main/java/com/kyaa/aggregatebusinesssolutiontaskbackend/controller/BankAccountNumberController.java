package com.kyaa.aggregatebusinesssolutiontaskbackend.controller;

import com.kyaa.aggregatebusinesssolutiontaskbackend.data.dto.request.GenerateAccountNumberRequest;
import com.kyaa.aggregatebusinesssolutiontaskbackend.exception.ApiResponse;
import com.kyaa.aggregatebusinesssolutiontaskbackend.service.BankAccountNumberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/bank-account-number/")
public class BankAccountNumberController {
    private final BankAccountNumberService bankAccountNumberService;

    @PostMapping("generate-account-number")
    public ResponseEntity<ApiResponse> generateAccountNumber(@RequestBody
                                             GenerateAccountNumberRequest generateAccountNumberRequest){
        return new ResponseEntity<>( bankAccountNumberService.generateAccountNumber(generateAccountNumberRequest), HttpStatus.CREATED);
    }
}
