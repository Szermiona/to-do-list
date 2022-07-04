package com.todolist.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "todolist.repository")
public class Beans {


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
