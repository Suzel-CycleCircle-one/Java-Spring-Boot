package com.Assement.week_3.College_management_system.College.management.system.services;

import com.Assement.week_3.College_management_system.College.management.system.dto.StudentDTO;
import com.Assement.week_3.College_management_system.College.management.system.entities.StudentEntity;
import com.Assement.week_3.College_management_system.College.management.system.repositires.StudentRepositires;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServices {

    private  final StudentRepositires studentRepositires;

    private  final ModelMapper modelMapper;

    public StudentServices(StudentRepositires studentRepositires, ModelMapper modelMapper) {
        this.studentRepositires = studentRepositires;
        this.modelMapper = modelMapper;
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        StudentEntity studentEntity = modelMapper.map(studentDTO, StudentEntity.class);
        studentEntity = studentRepositires.save(studentEntity);
         return modelMapper.map(studentEntity , StudentDTO.class);
    }


    public List<StudentDTO> getAllStudent() {
        return  studentRepositires.findAll().stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentByID(Long studentID) {
        StudentEntity studentEntity = studentRepositires.findById(studentID).orElse(null);
        return modelMapper.map(studentEntity, StudentDTO.class);
    }
}
