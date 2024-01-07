package com.example.employeemanagement.service;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServices {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeServices(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return employeeRepo.findAll();
    }

    public Employee updateEmployee(Employee employee){
        Long employeeId = employee.getId();
        Optional<Employee> optionalEmployee = employeeRepo.findById(employeeId);
        if(optionalEmployee.isPresent()){
            Employee existEmployee = optionalEmployee.get();
            existEmployee.setName(employee.getName()) ;
            existEmployee.setEmail(employee.getEmail());
            existEmployee.setPhone(employee.getPhone());
            return employeeRepo.save(employee);
        }
        return null ;


    }
    public void deleteEmployee(Long id){
        employeeRepo.deleteById(id);
    }

    public Employee findEmployeeById(Long Id){
        return employeeRepo.findById(Id).orElse(null);
    }
}
