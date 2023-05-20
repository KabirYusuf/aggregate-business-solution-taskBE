package com.kyaa.aggregatebusinesssolutiontaskbackend.data.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenerateAccountNumberRequest {
    @NotBlank(message = "Bank code is Mandatory")
    @Min(value = 3)
    @Max(value = 3)
    private String bankCode;
    @NotBlank(message = "Serial number is Mandatory")
    @Min(value = 9)
    @Max(value = 9)
    private String serialNumber;
}
