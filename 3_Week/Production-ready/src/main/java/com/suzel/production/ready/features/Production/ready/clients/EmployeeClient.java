package com.suzel.production.ready.features.Production.ready.clients;

import com.suzel.production.ready.features.Production.ready.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long employeeID);

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
}
