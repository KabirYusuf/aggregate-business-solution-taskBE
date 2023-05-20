package com.kyaa.aggregatebusinesssolutiontaskbackend.data.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
public class GenerateAccountNumberRequest {
    @NotBlank(message = "Bank code is Mandatory")
    @Length(min = 3, max = 3)
    private String bankCode;
    @NotBlank(message = "Serial number is Mandatory")
    @Length(min = 9, max = 9)
    private String serialNumber;
}
