package com.RESAPI.RESAPI.Suzel.controllers;


import com.RESAPI.RESAPI.Suzel.advices.ApiResponse;
import com.RESAPI.RESAPI.Suzel.dto.DepartmentDTO;
import com.RESAPI.RESAPI.Suzel.exceptions.ResourceNotFoundExceptions;
import com.RESAPI.RESAPI.Suzel.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartment(){
        return ResponseEntity.ok(departmentService.getAllDepartment());
    }

    @GetMapping(path = "/{departmentID}")
    public ResponseEntity<DepartmentDTO> get(@PathVariable(name = "departmentID") Long id ){
        Optional<DepartmentDTO> departmentDTO = departmentService.getDepartmentById(id);
        return departmentDTO
                .map(departmentDTO1 -> ResponseEntity.ok(departmentDTO1))
                .orElseThrow(() -> new ResourceNotFoundExceptions("Departement Not Found "+ id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentDTO>> createDepartment(@RequestBody @Valid DepartmentDTO departmentDTO) {
        DepartmentDTO departmentDTO1 = departmentService.saveDepartment(departmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(departmentDTO1));
    }

    @PutMapping(path = "/{departmentID}")
    public ResponseEntity<DepartmentDTO> updateDepartmentById(
            @PathVariable(name = "departmentID") Long id,
            @RequestBody DepartmentDTO departmentDTO){
        return ResponseEntity.ok(departmentService.updateDepartmentById(id , departmentDTO));
    }

    @DeleteMapping(path = "/{departmentID}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable(name = "departmentID") Long id){
        boolean getDelete  = departmentService.deleteDepartmentById(id);
        if(getDelete) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{departmentID}")
    public ResponseEntity<DepartmentDTO> updaatePartialDepartmentByID(@PathVariable(name = "departmentID" ) Long id ,
                                                           @RequestBody Map<String , Object> update ){

        DepartmentDTO departmentDTO = departmentService.updaatePartialDepartmentByID(id , update);
        if(departmentDTO == null) return ResponseEntity.notFound().build();
        return  ResponseEntity.ok(departmentDTO);
    }

}
