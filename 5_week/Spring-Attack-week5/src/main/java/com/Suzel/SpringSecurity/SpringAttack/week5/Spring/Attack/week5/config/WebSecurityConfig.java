package com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.config;


import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.filters.JwtAuthFilter;
import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.handlers.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.entities.enums.Role.ADMIN;
import static com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.entities.enums.Role.CREATOR;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private  final OAuth2SuccessHandler oAuth2SuccessHandler;
    private  static final String[] publicRoutes = {
            "/auth/**", "/home.html"
    };

      @Bean
      SecurityFilterChain  securityFilterChain(HttpSecurity httpSecurity) throws  Exception{

          httpSecurity.authorizeHttpRequests(auth -> auth
                          .requestMatchers(publicRoutes).permitAll()
                          .requestMatchers(HttpMethod.GET, "/posts/**").permitAll()
                          .requestMatchers(HttpMethod.POST, "/posts/**")
                          .hasAnyRole(ADMIN.name(), CREATOR.name())
                          .anyRequest().authenticated())
                  .csrf(csrfConfig -> csrfConfig.disable())
                  .sessionManagement(sessionConfig -> sessionConfig
                          .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                  .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                  .oauth2Login(oauth2Config -> oauth2Config
                          .failureUrl("/login?error=true")
                          .successHandler(oAuth2SuccessHandler))
          ;

          return httpSecurity.build();
      }

      @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
             return config.getAuthenticationManager();
      }

//
//      @Bean
//    UserDetailsService myInMemoryUserDetailsService(){
//          UserDetails normalUser = User
//                   .withUsername("suzel")
//                   .password(passwordEncoder().encode("pass"))
//                   .roles("USER")
//                   .build();
//
//          UserDetails adminUser = User
//                  .withUsername("admin")
//                  .password(passwordEncoder().encode("pass"))
//                  .roles("ADMIN")
//                  .build();
//          return new InMemoryUserDetailsManager(normalUser, adminUser);
//      }


}
