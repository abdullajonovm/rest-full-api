package com.example.companyrestfullapi.service;

import com.example.companyrestfullapi.dto.AddressDTO;
import com.example.companyrestfullapi.dto.ApiResponse;
import com.example.companyrestfullapi.entity.Address;
import com.example.companyrestfullapi.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {
    final AddressRepository addressRepository;

    public ApiResponse getAll() {
        List<Address> all = addressRepository.findAll();
        return new ApiResponse("Mana", true, all);
    }

    public ApiResponse getFindById(Integer id) {
        Optional<Address> byId = addressRepository.findById(id);
        if (byId.isPresent()) {
            return new ApiResponse("Mana", true, byId.get());
        } else {
            return new ApiResponse("The address in this id does not exist", false);

        }

    }

    public ApiResponse add(AddressDTO addressDTO) {
        if (addressRepository.existsByHomeNumberAndStreetNot(addressDTO.getHomeNumber(), addressDTO.getStreet())){
            return new ApiResponse("This address allready exist", false);
        }
        Address address = new Address();
        address.setHomeNumber(addressDTO.getHomeNumber());
        address.setStreet(addressDTO.getStreet());
        addressRepository.save(address);
        return new ApiResponse("This address succesfully added", true);
    }

    public ApiResponse edit(Integer id, AddressDTO addressDTO) {
        Optional<Address> byId = addressRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("The address in this id does not exist", false);
        }
        Address address = byId.get();
        address.setStreet(addressDTO.getStreet());
        address.setHomeNumber(addressDTO.getHomeNumber());
        addressRepository.save(address);
        return new ApiResponse("Succesfully edited", true);
    }

    public ApiResponse delet(Integer id) {
        Optional<Address> byId = addressRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("The address in this id does not exist", false);
        }
        addressRepository.deleteById(id);
        return new ApiResponse("Succesfully deleted", true);
    }
}
