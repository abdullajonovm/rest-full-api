package com.example.companyrestfullapi.controller;

import com.example.companyrestfullapi.dto.ApiResponse;
import com.example.companyrestfullapi.dto.DepartmentDTO;
import com.example.companyrestfullapi.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {
    final DepartmentService departmentService;

    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse  apiResponse = departmentService.getAll();
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        ApiResponse apiResponse = departmentService.getFindById(id);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody DepartmentDTO departmentDTO){
        ApiResponse apiResponse = departmentService.add(departmentDTO);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody DepartmentDTO departmentDTO){
        ApiResponse apiResponse = departmentService.edit(id, departmentDTO);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delet(@PathVariable Integer id){
        ApiResponse apiResponse = departmentService.delet(id);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }


}
