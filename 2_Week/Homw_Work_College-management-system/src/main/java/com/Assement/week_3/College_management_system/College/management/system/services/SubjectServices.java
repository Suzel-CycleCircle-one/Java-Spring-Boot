package com.Assement.week_3.College_management_system.College.management.system.services;

import com.Assement.week_3.College_management_system.College.management.system.dto.SubjectDTO;
import com.Assement.week_3.College_management_system.College.management.system.entities.SubjectEntity;
import com.Assement.week_3.College_management_system.College.management.system.repositires.SubjectRepositires;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServices {

    private final SubjectRepositires  subjectRepositires;

    private final ModelMapper modelMapper;

    public SubjectServices(SubjectRepositires subjectRepositires, ModelMapper modelMapper) {
        this.subjectRepositires = subjectRepositires;
        this.modelMapper = modelMapper;
    }

    public SubjectDTO createSubject(SubjectDTO subjectDTO) {
        SubjectEntity subjectEntity = modelMapper.map(subjectDTO, SubjectEntity.class);
        subjectEntity = subjectRepositires.save(subjectEntity);
        return modelMapper.map(subjectEntity, SubjectDTO.class);
    }

    public List<SubjectDTO> getAllSubject() {
        return subjectRepositires.findAll().stream()
        .map(subjectEntity -> modelMapper.map(subjectEntity, SubjectDTO.class))
                .collect(Collectors.toList());

    }

    public SubjectDTO getSubjectByID(Long subjectID) {
        SubjectEntity subjectEntity = subjectRepositires.findById(subjectID).orElse(null);
        return modelMapper.map(subjectEntity, SubjectDTO.class);
    }
}
