package com.example.companyrestfullapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {
    private String message;
    private Boolean succes;
    private Object object;

    public ApiResponse(String message, Boolean succes) {
        this.message = message;
        this.succes = succes;
    }
}
