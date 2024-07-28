package com.Assement.week_3.College_management_system.College.management.system.dto;

import com.Assement.week_3.College_management_system.College.management.system.entities.AdmissionRecordEntity;
import com.Assement.week_3.College_management_system.College.management.system.entities.StudentEntity;
import lombok.Data;

@Data
public class AdmisssionRecordDTO {
    private Long id;
    private Integer fee;
    private StudentEntity student;

}
