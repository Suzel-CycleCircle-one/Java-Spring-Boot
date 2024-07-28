package com.Assement.week_3.College_management_system.College.management.system.controllers;

import com.Assement.week_3.College_management_system.College.management.system.dto.ProfessorDTO;
import com.Assement.week_3.College_management_system.College.management.system.services.ProfessorServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/professor")
public class ProfessorController {

    private  final ProfessorServices professorServices;

    public ProfessorController(ProfessorServices professorServices) {
        this.professorServices = professorServices;
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> createProfessor(@RequestBody ProfessorDTO professor) {
        ProfessorDTO professorDTO = professorServices.createProfessor(professor);
        return ResponseEntity.ok(professorDTO);
    }

    @GetMapping
    public  ResponseEntity<List<ProfessorDTO>> getAllProfessor(){
       List<ProfessorDTO> professorDTO = professorServices.getAllProfessor();
        return ResponseEntity.ok(professorDTO);
    }

    @GetMapping(path = "/{professorID}")
    public ResponseEntity<ProfessorDTO> getProfessorByID(@PathVariable Long professorID){
        ProfessorDTO professorDTO = professorServices.getProfessorByID(professorID);
        return ResponseEntity.ok(professorDTO);
    }

    @PutMapping(path = "/{professorId}/subject/{subjectId}")
    public ResponseEntity<ProfessorDTO> professorOneToManyMappingWithSubject(@PathVariable Long professorId,
                                                                             @PathVariable Long subjectId){
        ProfessorDTO toSave = professorServices.professorOneToManyMappingWithSubject(professorId, subjectId);
        return new ResponseEntity<>(toSave, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{professorId}/student/{studentId}")
    public ResponseEntity<ProfessorDTO> professorManyToManyMappingWithStudent(@PathVariable Long professorId,
                                                                             @PathVariable Long studentId){
        ProfessorDTO toSave = professorServices.professorManyToManyMappingWithStudent(professorId, studentId);
        return new ResponseEntity<>(toSave, HttpStatus.CREATED);
    }
}
