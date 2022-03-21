package com.example.companyrestfullapi.controller;

import com.example.companyrestfullapi.dto.AddressDTO;
import com.example.companyrestfullapi.dto.ApiResponse;
import com.example.companyrestfullapi.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {
    final AddressService addressService;

    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse  apiResponse = addressService.getAll();
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        ApiResponse apiResponse = addressService.getFindById(id);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody AddressDTO addressDTO){
        ApiResponse apiResponse = addressService.add(addressDTO);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody AddressDTO addressDTO){
        ApiResponse apiResponse = addressService.edit(id, addressDTO);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delet(@PathVariable Integer id){
        ApiResponse apiResponse = addressService.delet(id);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }


}
