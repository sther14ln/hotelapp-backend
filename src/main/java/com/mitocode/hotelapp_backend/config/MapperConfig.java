package com.mitocode.hotelapp_backend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    /*@Bean //Para instanciar
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }*/

    @Bean(name = "genericMapper")
    public ModelMapper genericMapper() {

        return new ModelMapper();
    }
}
