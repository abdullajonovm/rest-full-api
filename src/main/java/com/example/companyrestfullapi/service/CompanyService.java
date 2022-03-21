package com.example.companyrestfullapi.service;

import com.example.companyrestfullapi.dto.ApiResponse;
import com.example.companyrestfullapi.dto.CompanyDTO;
import com.example.companyrestfullapi.entity.Address;
import com.example.companyrestfullapi.entity.Company;
import com.example.companyrestfullapi.repository.AddressRepository;
import com.example.companyrestfullapi.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    final AddressRepository addressRepository;
    final CompanyRepository companyRepository;

    public ApiResponse getAll() {
        List<Company> all = companyRepository.findAll();
        return new ApiResponse("Mana", true, all);
    }

    public ApiResponse getFindById(Integer id) {
        Optional<Company> byId = companyRepository.findById(id);
        if (byId.isPresent()) {
            return new ApiResponse("Mana", true, byId.get());
        } else {
            return new ApiResponse("The company in this id does not exist", false);

        }

    }

    public ApiResponse add(CompanyDTO companyDTO) {
        if (companyRepository.existsByCorpName(companyDTO.getCorpName())) {
            return new ApiResponse("This company name allready exist", false);
        }

        Optional<Address> addressOptional = addressRepository.findById(companyDTO.getAddressId());
        if (!addressOptional.isPresent()) {
            return new ApiResponse("The address in this id does not exist", false);
        }

        Company company = new Company();

        company.setAddress(addressOptional.get());
        company.setCorpName(companyDTO.getCorpName());
        company.setDirectorName(companyDTO.getDirectorName());
        companyRepository.save(company);

        return new ApiResponse("This company succesfully added", true);
    }

    public ApiResponse edit(Integer id, CompanyDTO companyDTO) {
        if (companyRepository.existsByCorpName(companyDTO.getCorpName())) {
            return new ApiResponse("This company name allready exist", false);
        }

        Optional<Address> addressOptional = addressRepository.findById(companyDTO.getAddressId());
        if (!addressOptional.isPresent()) {
            return new ApiResponse("The address in this id does not exist", false);
        }

        Optional<Company> companyOptional = companyRepository.findById(id);
        if (!companyOptional.isPresent()) {
            return new ApiResponse("The company in this id does not exist", false);
        }
        Company company = companyOptional.get();
        company.setAddress(addressOptional.get());
        company.setCorpName(companyDTO.getCorpName());
        company.setDirectorName(companyDTO.getDirectorName());
        companyRepository.save(company);

        return new ApiResponse("This company succesfully edited", true);

    }

    public ApiResponse delet(Integer id) {
            Optional<Company> byId = companyRepository.findById(id);
            if (!byId.isPresent()){
                return new ApiResponse("The company in this id does not exist", false);
            }
            companyRepository.deleteById(id);
            return new ApiResponse("Succesfully deleted", true);
    }
}
