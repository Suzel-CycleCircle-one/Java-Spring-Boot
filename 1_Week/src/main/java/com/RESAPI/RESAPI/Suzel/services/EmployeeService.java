package com.RESAPI.RESAPI.Suzel.services;
import com.RESAPI.RESAPI.Suzel.dto.EmployDTO;
import com.RESAPI.RESAPI.Suzel.entities.EmployeeEntity;
import com.RESAPI.RESAPI.Suzel.exceptions.ResourceNotFoundExceptions;
import com.RESAPI.RESAPI.Suzel.repositories.EmployeeRepository;
import lombok.Value;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private  final EmployeeRepository employeeRepository;
    private  final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployDTO.class)) ;
    }

    public List<EmployDTO> getAllEmployee() {
        List<EmployeeEntity>  employeeEntities  =employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployDTO.class))
                .collect(Collectors.toList());

    }

    public EmployDTO createNewEmployee(EmployDTO inputEmployee) {
        EmployeeEntity tosaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity employeeEntity =  employeeRepository.save(tosaveEntity);
        return modelMapper.map(employeeEntity, EmployDTO.class);
    }

    public EmployDTO updateEmployeeById(Long id, EmployDTO employDTO) {
        isExistsByEmployeeId(id);
     EmployeeEntity employeeEntity = modelMapper.map(employDTO, EmployeeEntity.class);
     employeeEntity.setId(id);
     EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
     return modelMapper.map(savedEmployeeEntity , EmployDTO.class);

    }

    public  void  isExistsByEmployeeId(Long id){
        boolean exists = employeeRepository.existsById(id);
        if(!exists) throw new ResourceNotFoundExceptions("Employee Not Found " + id);
    }


    public boolean deleteEmployeeById(Long employessID) {
        isExistsByEmployeeId(employessID);
       employeeRepository.deleteById(employessID);
       return true;
    }

    public EmployDTO updatePartialEmployeeById(Long id, Map<String, Object> updates) {
        boolean exists = employeeRepository.existsById(id);
        if (!exists) return null;

        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        updates.forEach((field, value) -> {
            Field fieldToUpdated = ReflectionUtils.findField(EmployeeEntity.class, field);
            fieldToUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdated , employeeEntity, value);
        });

        return modelMapper.map(employeeRepository.save(employeeEntity), EmployDTO.class);

    }
}
