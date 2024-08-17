package com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.dto;

import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.entities.enums.Permisson;
import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDTO {

    private String name;
    private String email;
    private String password;
    private Set<Role> roles;
    private Set<Permisson> permissons;
}
