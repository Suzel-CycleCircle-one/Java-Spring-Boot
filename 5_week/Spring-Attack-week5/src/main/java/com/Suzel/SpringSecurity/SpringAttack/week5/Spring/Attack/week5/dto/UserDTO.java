package com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.dto;


import lombok.*;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private  String email;

}
