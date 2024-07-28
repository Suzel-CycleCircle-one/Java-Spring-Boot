package com.Assement.week_3.College_management_system.College.management.system.controllers;

import com.Assement.week_3.College_management_system.College.management.system.dto.AdmisssionRecordDTO;
import com.Assement.week_3.College_management_system.College.management.system.services.AdmissionRecordServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admission_record")
public class AdmissionRecordController {

    private  final AdmissionRecordServices admissionRecordServices;


    public AdmissionRecordController(AdmissionRecordServices admissionRecordServices) {
        this.admissionRecordServices = admissionRecordServices;
    }

    @PostMapping
    public ResponseEntity<AdmisssionRecordDTO> createAdmissionRecord(@RequestBody  AdmisssionRecordDTO admisssionRecordDTO){
        AdmisssionRecordDTO admisssionRecordDTO1 = admissionRecordServices.createAdmissionRecord(admisssionRecordDTO);
        return ResponseEntity.ok(admisssionRecordDTO1);
    }

    @GetMapping
    public  ResponseEntity<List<AdmisssionRecordDTO>> getAllAdmisssionRecord(){
        List<AdmisssionRecordDTO> admisssionRecordDTO = admissionRecordServices.getAllAdmisssionRecord();
        return ResponseEntity.ok(admisssionRecordDTO);
    }

    @GetMapping(path = "/{admisssionRecordID}")
    public  ResponseEntity<AdmisssionRecordDTO> getAllAdmisssionRecordById(@PathVariable Long admisssionRecordID){
        AdmisssionRecordDTO admisssionRecordDTO = admissionRecordServices.getAllAdmisssionRecordById(admisssionRecordID);
        return ResponseEntity.ok(admisssionRecordDTO);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<AdmisssionRecordDTO> updateAdmissionRecord(@PathVariable Long id , @RequestBody AdmisssionRecordDTO admisssionRecordDTO){
        AdmisssionRecordDTO admisssionRecordDTO1 = admissionRecordServices.updateAdmissionRecord(id, admisssionRecordDTO);
        return ResponseEntity.ok(admisssionRecordDTO1);
    }

}
