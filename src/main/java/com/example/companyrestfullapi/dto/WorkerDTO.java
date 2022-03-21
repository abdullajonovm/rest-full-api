package com.example.companyrestfullapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkerDTO {
    private String name;
    private Integer addressId;
    private Integer departmentId;
}
