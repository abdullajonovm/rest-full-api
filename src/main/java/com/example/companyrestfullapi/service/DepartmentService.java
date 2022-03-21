package com.example.companyrestfullapi.service;

import com.example.companyrestfullapi.dto.ApiResponse;
import com.example.companyrestfullapi.dto.DepartmentDTO;
import com.example.companyrestfullapi.entity.Company;
import com.example.companyrestfullapi.entity.Department;
import com.example.companyrestfullapi.repository.CompanyRepository;
import com.example.companyrestfullapi.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    final CompanyRepository companyRepository;
    final DepartmentRepository departmentRepository;

    public ApiResponse getAll() {
        List<Department> all = departmentRepository.findAll();
        return new ApiResponse("Mana", true, all);
    }

    public ApiResponse getFindById(Integer id) {
        Optional<Department> byId = departmentRepository.findById(id);
        if (byId.isPresent()) {
            return new ApiResponse("Mana", true, byId.get());
        } else {
            return new ApiResponse("The department in this id does not exist", false);
        }
    }


    public ApiResponse add(DepartmentDTO departmentDTO) {
        Optional<Company> companyOptional = companyRepository.findById(departmentDTO.getCompanyId());
        if (!companyOptional.isPresent()) {
            return new ApiResponse("The company in this id does not exist", false);
        }

        Department department = new Department();
        department.setCompany(companyOptional.get());
        department.setName(departmentDTO.getName());
        departmentRepository.save(department);
        return new ApiResponse("This department succesfully added", true);
    }

    public ApiResponse edit(Integer id, DepartmentDTO departmentDTO) {
        Optional<Company> companyOptional = companyRepository.findById(departmentDTO.getCompanyId());
        if (!companyOptional.isPresent()) {
            return new ApiResponse("The comapany in this id does not exist", false);
        }
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (!departmentOptional.isPresent()) {
            return new ApiResponse("The department in this id does not exist", false);
        }
        Department department = departmentOptional.get();
        department.setCompany(companyOptional.get());
        department.setName(departmentDTO.getName());
        departmentRepository.save(department);
        return new ApiResponse("This department succesfully edited", true);
    }


    public ApiResponse delet(Integer id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (!departmentOptional.isPresent()) {
            return new ApiResponse("The department in this id does not exist", false);
        }
        departmentRepository.deleteById(id);
        return new ApiResponse("This department succesfully deleted", true);
    }

}
