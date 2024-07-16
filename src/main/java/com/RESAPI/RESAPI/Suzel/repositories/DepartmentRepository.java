package com.RESAPI.RESAPI.Suzel.repositories;


import com.RESAPI.RESAPI.Suzel.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
