package com.suzel.production.ready.features.Production.ready;

import com.suzel.production.ready.features.Production.ready.clients.EmployeeClient;
import com.suzel.production.ready.features.Production.ready.dto.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class ProductionReadyApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;

	@Test
	void getAllEmployees() {
		List<EmployeeDTO> employeeDTOS = employeeClient.getAllEmployees();
		System.out.println(employeeDTOS);
	}

	@Test
	void getByIdEmployees() {
		EmployeeDTO employeeDTOS = employeeClient.getEmployeeById(1L);
		System.out.println(employeeDTOS);
	}

	@Test
	void createNewEmployee(){
		EmployeeDTO employeeDTO;
        employeeDTO = new EmployeeDTO(null, "Suzel", "Suzel@gmail.com",
50, LocalDate.of(2020, 12, 1), true, "ADMIN");

		EmployeeDTO saveEmployeDTO = employeeClient.createEmployee(employeeDTO);
		System.out.println(saveEmployeDTO);

    }

}
