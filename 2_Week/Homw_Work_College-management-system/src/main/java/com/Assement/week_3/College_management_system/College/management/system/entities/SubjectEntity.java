// SubjectEntity.java
package com.Assement.week_3.College_management_system.College.management.system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Subject")
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "professor_Id", referencedColumnName = "id")
    @JsonIgnore
    private ProfessorEntity professor;
}


