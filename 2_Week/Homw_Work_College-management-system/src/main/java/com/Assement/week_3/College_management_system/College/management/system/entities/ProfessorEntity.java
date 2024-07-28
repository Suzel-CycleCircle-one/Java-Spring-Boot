package com.Assement.week_3.College_management_system.College.management.system.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Professor")
public class ProfessorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "professor",  fetch =  FetchType.LAZY)
    private List<SubjectEntity> subjects;

    @ManyToMany
    @JoinTable(name = "teaching_department" ,
          joinColumns =  @JoinColumn(name = "professoer_id") , inverseJoinColumns =  @JoinColumn(name = "student_id"))
   @JsonIgnore
    private  List<StudentEntity> students;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessorEntity that = (ProfessorEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle());
    }
}

