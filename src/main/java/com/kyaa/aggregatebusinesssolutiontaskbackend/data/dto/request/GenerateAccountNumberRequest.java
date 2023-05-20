package com.kyaa.aggregatebusinesssolutiontaskbackend.data.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenerateAccountNumberRequest {
    private String bankCode;
    private String serialNumber;
}
