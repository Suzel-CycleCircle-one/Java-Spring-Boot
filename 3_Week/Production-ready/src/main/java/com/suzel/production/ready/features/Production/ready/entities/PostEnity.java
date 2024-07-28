package com.suzel.production.ready.features.Production.ready.entities;


import jakarta.persistence.*;
import lombok.*;

import javax.annotation.processing.Generated;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class PostEnity extends  AuditableEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long Id;
    private  String name;
    private  String title;
}
