package com.RESAPI.RESAPI.Suzel.services;


import com.RESAPI.RESAPI.Suzel.dto.DepartmentDTO;
import com.RESAPI.RESAPI.Suzel.entities.DepartmentEntity;
import com.RESAPI.RESAPI.Suzel.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {


    private final DepartmentRepository departmentRepository;
    private  final ModelMapper modelMapper;


    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public DepartmentDTO saveDepartment(DepartmentDTO departmentInput){
        DepartmentEntity saveEntity = modelMapper.map(departmentInput, DepartmentEntity.class);
        DepartmentEntity departmentEntity = departmentRepository.save(saveEntity);
        return modelMapper.map(departmentEntity, DepartmentDTO.class);
    }

    public List<DepartmentDTO> getAllDepartment() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<DepartmentDTO> getDepartmentById(Long id) {
        return departmentRepository.findById(id).map(departmentEntity -> modelMapper.map(departmentEntity , DepartmentDTO.class ));
    }

    public DepartmentDTO updateDepartmentById(Long id, DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity = modelMapper.map(departmentDTO, DepartmentEntity.class);
        departmentEntity.setId(id);
        DepartmentEntity saveDepartment = departmentRepository.save(departmentEntity);
        return modelMapper.map(saveDepartment , DepartmentDTO.class);
    }

    public boolean deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
        return true;
    }

    public DepartmentDTO updaatePartialDepartmentByID(Long id, Map<String, Object> update) {

           DepartmentEntity departmentEntity = departmentRepository.findById(id).get();
           update.forEach((field , value) -> {
               Field fieldToUpdate = ReflectionUtils.findField(DepartmentEntity.class, field);
               fieldToUpdate.setAccessible(true);
               ReflectionUtils.setField(fieldToUpdate, departmentEntity, value);
           });

           return  modelMapper.map(departmentRepository.save(departmentEntity), DepartmentDTO.class);
    }
}
