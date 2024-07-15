package com.RESAPI.RESAPI.Suzel.repositories;

import com.RESAPI.RESAPI.Suzel.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
