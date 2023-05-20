package com.kyaa.aggregatebusinesssolutiontaskbackend.service.implementation;

import com.kyaa.aggregatebusinesssolutiontaskbackend.data.dto.request.GenerateAccountNumberRequest;
import com.kyaa.aggregatebusinesssolutiontaskbackend.data.dto.response.GenerateBankAccountResponse;
import com.kyaa.aggregatebusinesssolutiontaskbackend.data.model.BankAccountNumber;
import com.kyaa.aggregatebusinesssolutiontaskbackend.data.repository.BankAccountNumberRepository;
import com.kyaa.aggregatebusinesssolutiontaskbackend.exception.ApiResponse;
import com.kyaa.aggregatebusinesssolutiontaskbackend.exception.BankAccountNumberException;
import com.kyaa.aggregatebusinesssolutiontaskbackend.service.BankAccountNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class NigerianBankAccountNumberService implements BankAccountNumberService {
    private final BankAccountNumberRepository bankAccountNumberRepository;
    @Override
    public ApiResponse generateAccountNumber(GenerateAccountNumberRequest generateAccountNumberRequest) {
        if (bankAccountNumberRepository.findBankAccountNumberByBankCodeAndSerialNumber(generateAccountNumberRequest.getBankCode(),
                generateAccountNumberRequest.getSerialNumber()).isPresent()) throw new BankAccountNumberException("serial number "+
                generateAccountNumberRequest.getSerialNumber() + " already exist in the specified bank");
        if (!validateBankCode(generateAccountNumberRequest.getBankCode()))throw new BankAccountNumberException("Bank code cant be less than or equal to zero");
        String nuban = generateNuban(generateAccountNumberRequest.getBankCode(),
                generateAccountNumberRequest.getSerialNumber());

        buildBankAccountObject(generateAccountNumberRequest, nuban);

        GenerateBankAccountResponse generateBankAccountResponse = getGenerateBankAccountResponse(generateAccountNumberRequest, nuban);
        return getApiResponse(generateBankAccountResponse);
    }

    private boolean validateBankCode(String bankCode) {
        int bankCodeIntegerValue = Integer.parseInt(bankCode);
        return bankCodeIntegerValue > 0;
    }


    private ApiResponse getApiResponse(GenerateBankAccountResponse generateBankAccountResponse) {
        return ApiResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .timeStamp(ZonedDateTime.now())
                .data(generateBankAccountResponse)
                .isSuccessful(true)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    private GenerateBankAccountResponse getGenerateBankAccountResponse(GenerateAccountNumberRequest generateAccountNumberRequest, String nuban) {
        return GenerateBankAccountResponse.builder()
                .generatedAccountNumber(nuban)
                .bankCode(generateAccountNumberRequest.getBankCode())
                .serialNumber(generateAccountNumberRequest.getSerialNumber())
                .build();
    }

    private void buildBankAccountObject(GenerateAccountNumberRequest generateAccountNumberRequest, String nuban) {
        BankAccountNumber bankAccountNumber = new BankAccountNumber();
        bankAccountNumber.setBankCode(generateAccountNumberRequest.getBankCode());
        bankAccountNumber.setSerialNumber(generateAccountNumberRequest.getSerialNumber());
        bankAccountNumber.setAccountNumber(nuban);
        bankAccountNumberRepository.save(bankAccountNumber);
    }

    private String generateNuban(String bankCode, String serialNumber) {
        String concatenateBankCodeAndSerialNumber = bankCode + serialNumber;
        long numericValueOfConcatenateBankCodeAndSerialNumber = Long.parseLong(concatenateBankCodeAndSerialNumber);
        long startCharacterForBankNumber = numericValueOfConcatenateBankCodeAndSerialNumber - BigInteger.valueOf(1_000_000_000).longValue();
        String uniqueStartCharacterForBankNumberToString = String.valueOf(startCharacterForBankNumber);
        long checkNumber = numericValueOfConcatenateBankCodeAndSerialNumber % 10;
        String concatenateBankCodeSerialNumberAndCheckNumber = concatenateBankCodeAndSerialNumber + checkNumber;
        return uniqueStartCharacterForBankNumberToString.substring(0,2) +
                concatenateBankCodeSerialNumberAndCheckNumber.substring(5);
    }
}
