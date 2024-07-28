package com.Assement.week_3.College_management_system.College.management.system.dto;

import com.Assement.week_3.College_management_system.College.management.system.entities.AdmissionRecordEntity;
import com.Assement.week_3.College_management_system.College.management.system.entities.ProfessorEntity;
import com.Assement.week_3.College_management_system.College.management.system.entities.StudentEntity;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;

    private String name;
    private AdmissionRecordEntity student_fee;
    private List<ProfessorEntity> professers;
}
