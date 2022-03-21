package com.example.companyrestfullapi.controller;

import com.example.companyrestfullapi.dto.CompanyDTO;
import com.example.companyrestfullapi.dto.ApiResponse;
import com.example.companyrestfullapi.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {
    final CompanyService companyService;

    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse  apiResponse = companyService.getAll();
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        ApiResponse apiResponse = companyService.getFindById(id);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody CompanyDTO companyDTO){
        ApiResponse apiResponse = companyService.add(companyDTO);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody CompanyDTO companyDTO){
        ApiResponse apiResponse = companyService.edit(id, companyDTO);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delet(@PathVariable Integer id){
        ApiResponse apiResponse = companyService.delet(id);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }


}
