package com.example.companyrestfullapi.service;

import com.example.companyrestfullapi.dto.ApiResponse;
import com.example.companyrestfullapi.dto.WorkerDTO;
import com.example.companyrestfullapi.entity.Address;
import com.example.companyrestfullapi.entity.Department;
import com.example.companyrestfullapi.entity.Worker;
import com.example.companyrestfullapi.repository.AddressRepository;
import com.example.companyrestfullapi.repository.DepartmentRepository;
import com.example.companyrestfullapi.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkerService {
    final DepartmentRepository departmentRepository;
    final AddressRepository addressRepository;
    final WorkerRepository workerRepository;

    public ApiResponse getAll() {
        List<Worker> all = workerRepository.findAll();
        return new ApiResponse("Mana", true, all);
    }

    public ApiResponse getFindById(Integer id) {
        Optional<Worker> byId = workerRepository.findById(id);
        if (byId.isPresent()) {
            return new ApiResponse("Mana", true, byId.get());
        } else {
            return new ApiResponse("The worker in this id does not exist", false);
        }
    }


    public ApiResponse add(WorkerDTO workerDTO) {
        Optional<Address> addressOptional = addressRepository.findById(workerDTO.getAddressId());

        if (!addressOptional.isPresent()) {
            return new ApiResponse("The address in this id does not exist", false);
        }

        Optional<Department> departmentOptional = departmentRepository.findById(workerDTO.getDepartmentId());

        if (!departmentOptional.isPresent()) {
            return new ApiResponse("The department in this id does not exist", false);
        }

        Worker worker = new Worker();
        worker.setAddress(addressOptional.get());
        worker.setDepartment(departmentOptional.get());
        worker.setName(workerDTO.getName());
        workerRepository.save(worker);
        return new ApiResponse("This worker succcesfully added", true);
    }

    public ApiResponse edit(Integer id, WorkerDTO workerDTO) {
        Optional<Address> addressOptional = addressRepository.findById(workerDTO.getAddressId());

        if (!addressOptional.isPresent()) {
            return new ApiResponse("The address in this id does not exist", false);
        }

        Optional<Department> departmentOptional = departmentRepository.findById(workerDTO.getDepartmentId());

        if (!departmentOptional.isPresent()) {
            return new ApiResponse("The department in this id does not exist", false);
        }
        Optional<Worker> workerOptional = workerRepository.findById(id);
        if (!workerOptional.isPresent()){
            return new ApiResponse("The worker in this id does not exist", false);
        }
        Worker worker = workerOptional.get();
        worker.setAddress(addressOptional.get());
        worker.setDepartment(departmentOptional.get());
        worker.setName(workerDTO.getName());
        workerRepository.save(worker);
        return new ApiResponse("This worker succcesfully edited", true);

    }

    public ApiResponse delet(Integer id) {
        Optional<Worker> byId = workerRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("The worker in this id does not exist", false);
        }
        workerRepository.deleteById(id);
        return new ApiResponse("This worker succesfully deleted", true);
    }
}
