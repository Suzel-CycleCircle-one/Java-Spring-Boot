package com.Assement.week_3.College_management_system.College.management.system.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "student")
    @JsonBackReference
    private AdmissionRecordEntity student_fee;

    @ManyToMany(mappedBy = "students")
    private List<ProfessorEntity> professers;
}
