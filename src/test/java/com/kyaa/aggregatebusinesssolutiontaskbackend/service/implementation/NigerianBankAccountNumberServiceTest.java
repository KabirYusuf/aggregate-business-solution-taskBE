package com.kyaa.aggregatebusinesssolutiontaskbackend.service.implementation;

import com.kyaa.aggregatebusinesssolutiontaskbackend.data.dto.request.GenerateAccountNumberRequest;
import com.kyaa.aggregatebusinesssolutiontaskbackend.data.dto.response.GenerateBankAccountResponse;
import com.kyaa.aggregatebusinesssolutiontaskbackend.data.model.BankAccountNumber;
import com.kyaa.aggregatebusinesssolutiontaskbackend.data.repository.BankAccountNumberRepository;
import com.kyaa.aggregatebusinesssolutiontaskbackend.exception.ApiResponse;
import com.kyaa.aggregatebusinesssolutiontaskbackend.exception.BankAccountNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

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

    }


    @Test
    void testAccountNumberIsGenerated(){
        generateAccountNumberRequest.setSerialNumber("999999999");
        generateAccountNumberRequest.setBankCode("011");
        ApiResponse apiResponse = nigerianBankAccountNumberService.generateAccountNumber(generateAccountNumberRequest);
        assertNotNull(apiResponse);
    }
    @Test
    void testAccountNumberGeneratedLengthIsTen(){
        generateAccountNumberRequest.setSerialNumber("786543902");
        generateAccountNumberRequest.setBankCode("011");
        ApiResponse apiResponse = nigerianBankAccountNumberService.generateAccountNumber(generateAccountNumberRequest);
        assertNotNull(apiResponse);
        GenerateBankAccountResponse response = (GenerateBankAccountResponse) apiResponse.getData();
        int generatedNubanLength = response.getGeneratedAccountNumber().length();
        assertEquals(BigInteger.valueOf(10).intValue(), generatedNubanLength);
    }
    @Test
    void testThatAccountNumberIsSaved(){
        generateAccountNumberRequest.setSerialNumber("999999999");
        generateAccountNumberRequest.setBankCode("011");
        BankAccountNumber savedBankAccount = new BankAccountNumber();

        given(bankAccountNumberRepository.save(bankAccountNumberArgumentCaptor.capture())).willReturn(savedBankAccount);

        ApiResponse apiResponse = nigerianBankAccountNumberService.generateAccountNumber(generateAccountNumberRequest);

        BDDMockito.then(bankAccountNumberRepository)
                .should()
                .save(bankAccountNumberArgumentCaptor.capture());
    }
    @Test
    void testThatExceptionIsThrownIfAUserTriesToGenerateANewAccountNumberWithASerialNumberThatIsAlreadyExistingInAParticularBank(){
        generateAccountNumberRequest.setSerialNumber("999999999");
        generateAccountNumberRequest.setBankCode("011");

        when(bankAccountNumberRepository.findBankAccountNumberByBankCodeAndSerialNumber(anyString(), anyString()))
                .thenReturn(Optional.of(new BankAccountNumber()));

        assertThrows(BankAccountNumberException.class, ()->nigerianBankAccountNumberService.generateAccountNumber(generateAccountNumberRequest));

        verify(bankAccountNumberRepository,never()).save(bankAccountNumberArgumentCaptor.capture());

    }
}