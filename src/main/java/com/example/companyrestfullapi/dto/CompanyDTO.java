package com.example.companyrestfullapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyDTO {
    private String corpName;
    private String directorName;
   private Integer addressId;
}
