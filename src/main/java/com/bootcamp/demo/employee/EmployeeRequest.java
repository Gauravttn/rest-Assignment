package com.bootcamp.demo.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class EmployeeRequest {

    @Autowired
    private EmployeeService service;

    @GetMapping(path = "emp")
    public List<Employee> getAll(){
        return service.findAll();
    }

    @GetMapping(path = "emp/{id}")
    public Employee getOne(@PathVariable int id)  {
        Employee obj = service.findOne(id);
        if(obj==null){
            throw new EmployeeNotFoundException("id : "+id);
        }
        return obj;
    }

    @PostMapping(path = "emp")
    public ResponseEntity<Object> add(@Valid @RequestBody Employee e){
        Employee a= service.empAdd(e);

        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(a.getId()).toUri();

        return ResponseEntity.created(location).build();

    }
    @DeleteMapping(path="emp/del/{id}")
    public String delEmp(@PathVariable int id){
        String s=service.delEmployee(id);
        if(s==null){
            throw new EmployeeNotFoundException("ID : "+id);
        }
        return s;
    }

    @PutMapping("emp/update")
    public String updateEmployee(@Valid @RequestBody Employee employee) {
        Employee employee1 = service.updateEmployeeDetails(employee);
        if (employee1 == null)
            throw new EmployeeNotFoundException("ID not found : "+employee.getId());

        return "Updating Success";
    }



}
