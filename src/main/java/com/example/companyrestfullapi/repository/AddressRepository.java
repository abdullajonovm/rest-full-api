package com.example.companyrestfullapi.repository;

import com.example.companyrestfullapi.entity.Address;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    boolean existsByHomeNumberAndStreetNot(String homeNumber, String street);
}