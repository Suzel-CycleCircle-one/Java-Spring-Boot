package com.suzel.production.ready.features.Production.ready.clients.impl;

import com.suzel.production.ready.features.Production.ready.clients.EmployeeClient;
import com.suzel.production.ready.features.Production.ready.dto.EmployeeDTO;
import com.suzel.production.ready.features.Production.ready.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private  final RestClient restClient;

    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.info("get ALL Employee List");
       try{
           List<EmployeeDTO> employeeDTOList = restClient.get()
                   .uri("employees")
                   .retrieve().body(new ParameterizedTypeReference<>() {
                   });

           return employeeDTOList;
       }catch (Exception e){
           log.error("Excepetion Occcure  Get All Employeee" , e);
           throw  new RuntimeException(e);
       }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeID) {
        try{
            EmployeeDTO employeeDTOList = restClient.get()
                    .uri("employees/{employeesID}", employeeID)
                    .retrieve().body(new ParameterizedTypeReference<>() {
                    });
            return employeeDTOList;
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        try{
            EmployeeDTO employeeDTOList = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError , (req, res) ->{
                        throw  new ResourceNotFoundException("could not create the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDTOList;
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
}
