package com.RESAPI.RESAPI.Suzel.controllers;
import com.RESAPI.RESAPI.Suzel.advices.ApiResponse;
import com.RESAPI.RESAPI.Suzel.dto.EmployDTO;
import com.RESAPI.RESAPI.Suzel.exceptions.ResourceNotFoundExceptions;
import com.RESAPI.RESAPI.Suzel.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/employees")
public class EmployController {
  private final EmployeeService employeeService;

    public EmployController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeesID}")
    public ResponseEntity<EmployDTO> getEmploy(@PathVariable(name = "employeesID") Long id){
       Optional<EmployDTO> employDTO =  employeeService.getEmployeeById(id);
       return employDTO
               .map(employDTO1 -> ResponseEntity.ok(employDTO1))
               .orElseThrow( ()-> new ResourceNotFoundExceptions("Employee Not Found " + id));
    }



    @GetMapping
    public  ResponseEntity<List<EmployDTO>> getEmployee(@RequestParam(required = false, name = "InputAge") Integer age ,
                                            @RequestParam(required = false) String Name ){
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EmployDTO>> createEmployee(@RequestBody @Valid EmployDTO inputEmployee ){
        EmployDTO saveEmploy =  employeeService.createNewEmployee(inputEmployee);
        return ResponseEntity.status( HttpStatus.CREATED).body(new ApiResponse<>(saveEmploy));
    }


    @PutMapping(path = "/{employeesID}")
    public  ResponseEntity<EmployDTO>  updateEmployeeById(@PathVariable(name = "employeesID") Long id , @RequestBody EmployDTO employDTO){
      return ResponseEntity.ok(employeeService.updateEmployeeById(id, employDTO));
    }

    @DeleteMapping(path = "/{employessID}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employessID){
        boolean gotDelete = employeeService.deleteEmployeeById(employessID);
        if(gotDelete) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping (path = "/{employeesID}")
    public ResponseEntity<EmployDTO>  updatePartialEmployeeById(@PathVariable(name = "employeesID") Long id ,
                                                 @RequestBody Map<String , Object> updates){
    EmployDTO employDTO = employeeService.updatePartialEmployeeById(id, updates);
    if(employDTO == null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(employDTO);
    }

}
