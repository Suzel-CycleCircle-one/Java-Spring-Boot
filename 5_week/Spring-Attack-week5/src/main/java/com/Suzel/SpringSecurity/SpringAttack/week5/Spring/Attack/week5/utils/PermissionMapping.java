package com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.utils;

import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.entities.enums.Permisson;
import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.entities.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.entities.enums.Permisson.*;
import static com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.entities.enums.Role.*;

public class PermissionMapping {

  private  static  final  Map<Role, Set<Permisson>> map = Map.of(
            USER, Set.of(USER_VIEW, POST_VIEW),
            CREATOR , Set.of(POST_CREATE, USER_UPDATE, POST_UPDATE),
            ADMIN, Set.of(POST_CREATE, USER_UPDATE, POST_UPDATE, USER_DELETE, USER_CREATE, POST_DELETE)
    );

    public  static  Set<SimpleGrantedAuthority> getAuthoritiesForRole(Role role){
        return map.get(role).stream()
                .map(permisson -> new SimpleGrantedAuthority(permisson.name()))
                .collect(Collectors.toSet());
    }


}
