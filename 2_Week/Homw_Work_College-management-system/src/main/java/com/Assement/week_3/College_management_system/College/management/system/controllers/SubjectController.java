package com.Assement.week_3.College_management_system.College.management.system.controllers;

import com.Assement.week_3.College_management_system.College.management.system.dto.SubjectDTO;
import com.Assement.week_3.College_management_system.College.management.system.services.SubjectServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/subject")
public class SubjectController {

    private final SubjectServices subjectServices;


    public SubjectController(SubjectServices subjectServices) {
        this.subjectServices = subjectServices;
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody SubjectDTO subjectDTO){
        SubjectDTO subjectDTO1 = subjectServices.createSubject(subjectDTO);
        return  ResponseEntity.ok(subjectDTO1);
    }

    @GetMapping
    public  ResponseEntity<List<SubjectDTO>> getAllSubject(){
        List<SubjectDTO> subjectDTO = subjectServices.getAllSubject();
        return ResponseEntity.ok(subjectDTO);
    }

    @GetMapping(path = "/{subjectID}")
    public  ResponseEntity<SubjectDTO> getSubjectByID(@PathVariable Long subjectID){
        SubjectDTO subjectDTO = subjectServices.getSubjectByID(subjectID);
        return ResponseEntity.ok(subjectDTO);
    }
}
