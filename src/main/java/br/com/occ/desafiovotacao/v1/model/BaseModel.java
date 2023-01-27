package br.com.occ.desafiovotacao.v1.model;

import org.modelmapper.ModelMapper;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseModel {

    public abstract Long getId();

    public <D> D toDto(ModelMapper modelMapper, Class<D> destinationType) {
        return modelMapper.map(this, destinationType);
    }

}
