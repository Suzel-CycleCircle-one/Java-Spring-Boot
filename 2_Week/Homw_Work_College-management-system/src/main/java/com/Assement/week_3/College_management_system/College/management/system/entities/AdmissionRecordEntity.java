package com.Assement.week_3.College_management_system.College.management.system.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "admission_record")
public class AdmissionRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer fee;

    @OneToOne
    @JoinColumn(name = "Student_ID")
    @JsonBackReference
    private StudentEntity student;

}
