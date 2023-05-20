package com.kyaa.aggregatebusinesssolutiontaskbackend.data.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class GenerateBankAccountResponse {
    private String bankCode;
    private String serialNumber;
    private String generatedAccountNumber;
}
