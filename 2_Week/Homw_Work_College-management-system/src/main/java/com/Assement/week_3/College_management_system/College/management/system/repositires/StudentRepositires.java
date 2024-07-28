package com.Assement.week_3.College_management_system.College.management.system.repositires;

import com.Assement.week_3.College_management_system.College.management.system.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositires extends JpaRepository<StudentEntity, Long> {
}
