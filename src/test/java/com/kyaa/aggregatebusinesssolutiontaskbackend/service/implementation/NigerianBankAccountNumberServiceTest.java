package com.kyaa.aggregatebusinesssolutiontaskbackend.service.implementation;

import com.kyaa.aggregatebusinesssolutiontaskbackend.data.dto.request.GenerateAccountNumberRequest;
import com.kyaa.aggregatebusinesssolutiontaskbackend.data.model.BankAccountNumber;
import com.kyaa.aggregatebusinesssolutiontaskbackend.data.repository.BankAccountNumberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)

class NigerianBankAccountNumberServiceTest {
    @InjectMocks
    private NigerianBankAccountNumberService nigerianBankAccountNumberService;
    @Mock
    private BankAccountNumberRepository bankAccountNumberRepository;
    @Captor
    private ArgumentCaptor<BankAccountNumber> bankAccountNumberArgumentCaptor;
    private GenerateAccountNumberRequest generateAccountNumberRequest;
    @BeforeEach
    void setup(){
        generateAccountNumberRequest = new GenerateAccountNumberRequest();
        generateAccountNumberRequest.setSerialNumber("999999999");
        generateAccountNumberRequest.setBankCode("011");
    }


    @Test
    void testThatAccountNumberCanBeGenerated(){
        given(bankAccountNumberRepository)
    }
}