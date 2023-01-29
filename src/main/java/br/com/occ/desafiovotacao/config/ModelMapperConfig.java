package br.com.occ.desafiovotacao.config;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull())
                .isImplicitMappingEnabled();
        return modelMapper;
    }
}
