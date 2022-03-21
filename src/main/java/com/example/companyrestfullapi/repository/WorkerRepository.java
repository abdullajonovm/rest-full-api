package com.example.companyrestfullapi.repository;

import com.example.companyrestfullapi.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
}