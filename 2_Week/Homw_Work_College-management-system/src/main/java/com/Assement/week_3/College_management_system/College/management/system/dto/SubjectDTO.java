// SubjectDTO.java
package com.Assement.week_3.College_management_system.College.management.system.dto;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
  private Long id;
  private  String title;
   private ProfessorDTO professor;

}



