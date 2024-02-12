package com.rhpro.configs;

import com.rhpro.utils.ConversaoData;
import com.rhpro.utils.ConversaoSalario;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        var mapper = new ModelMapper();

        mapper.addConverter(new ConversaoData());
        mapper.addConverter(new ConversaoSalario());

        mapper.cre

        return mapper;
    }
}
