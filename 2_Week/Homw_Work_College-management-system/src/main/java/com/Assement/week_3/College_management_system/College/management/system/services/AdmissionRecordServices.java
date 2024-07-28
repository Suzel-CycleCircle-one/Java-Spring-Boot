package com.Assement.week_3.College_management_system.College.management.system.services;

import com.Assement.week_3.College_management_system.College.management.system.dto.AdmisssionRecordDTO;
import com.Assement.week_3.College_management_system.College.management.system.entities.AdmissionRecordEntity;
import com.Assement.week_3.College_management_system.College.management.system.repositires.AdmissionReacordRepositires;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdmissionRecordServices {

    private  final AdmissionReacordRepositires admissionReacordRepositires;

    private  final ModelMapper modelMapper;

    public AdmissionRecordServices(AdmissionReacordRepositires admissionReacordRepositires, ModelMapper modelMapper) {
        this.admissionReacordRepositires = admissionReacordRepositires;
        this.modelMapper = modelMapper;
    }

    public AdmisssionRecordDTO createAdmissionRecord(AdmisssionRecordDTO admisssionRecordDTO) {
        AdmissionRecordEntity admissionRecordEntity = modelMapper.map(admisssionRecordDTO, AdmissionRecordEntity.class);
        admissionRecordEntity = admissionReacordRepositires.save(admissionRecordEntity);
        return modelMapper.map(admissionRecordEntity , AdmisssionRecordDTO.class);
    }

    public List<AdmisssionRecordDTO> getAllAdmisssionRecord() {
        return admissionReacordRepositires.findAll().stream()
                .map(admissionRecordEntity -> modelMapper.map(admissionRecordEntity , AdmisssionRecordDTO.class))
                .collect(Collectors.toList());
    }

    public AdmisssionRecordDTO getAllAdmisssionRecordById(Long admisssionRecordID) {
        AdmissionRecordEntity admissionRecordEntity = admissionReacordRepositires.findById(admisssionRecordID).orElse(null);
        return modelMapper.map(admissionRecordEntity, AdmisssionRecordDTO.class);
    }

    public AdmisssionRecordDTO updateAdmissionRecord(Long id, AdmisssionRecordDTO admisssionRecordDTO) {
        AdmissionRecordEntity admissionRecordEntity = modelMapper.map(admisssionRecordDTO , AdmissionRecordEntity.class);
        admissionRecordEntity.setId(id);
        AdmissionRecordEntity saveAdmissionRecordEntity = admissionReacordRepositires.save(admissionRecordEntity);
        return modelMapper.map(saveAdmissionRecordEntity , AdmisssionRecordDTO.class);
    }
}
