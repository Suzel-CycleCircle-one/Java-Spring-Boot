package com.Assement.week_3.College_management_system.College.management.system.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper getModelMapper(){
          return new ModelMapper();
      }
}
