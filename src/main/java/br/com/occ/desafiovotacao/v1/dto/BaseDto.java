package br.com.occ.desafiovotacao.v1.dto;

import org.modelmapper.ModelMapper;

public abstract class BaseDto {

    public <D> D toEntity(ModelMapper modelMapper, Class<D> d) {
        return modelMapper.map(this, d);
    }

    public <D> D toEntity(ModelMapper modelMapper, D d) {
        modelMapper.map(this, d);
        return d;
    }
}
