package com.Assement.week_3.College_management_system.College.management.system.controllers;


import com.Assement.week_3.College_management_system.College.management.system.dto.StudentDTO;
import com.Assement.week_3.College_management_system.College.management.system.services.StudentServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

      private final StudentServices studentServices;

    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @PostMapping
    public ResponseEntity<StudentDTO> creteStudent(@RequestBody StudentDTO studentDTO){
        StudentDTO studentDTO1 = studentServices.createStudent(studentDTO);
        return ResponseEntity.ok(studentDTO1);
    }

    @GetMapping
    public  ResponseEntity<List<StudentDTO>> getAllStudent(){
       List<StudentDTO> studentDTO =  studentServices.getAllStudent();
       return ResponseEntity.ok(studentDTO);
    }

    @GetMapping(path = "/{studentID}")
    public  ResponseEntity<StudentDTO> getStudentById(@PathVariable Long studentID  ){
        StudentDTO studentDTO1 = studentServices.getStudentByID(studentID);
        return ResponseEntity.ok(studentDTO1);
    }
}
