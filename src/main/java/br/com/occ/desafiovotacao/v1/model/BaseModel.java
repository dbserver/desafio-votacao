package br.com.occ.desafiovotacao.v1.model;

import lombok.EqualsAndHashCode;
import org.modelmapper.ModelMapper;

import javax.persistence.MappedSuperclass;
@EqualsAndHashCode
@MappedSuperclass
public abstract class BaseModel {

    public abstract Long getId();

    public <D> D toDto(ModelMapper modelMapper, Class<D> destinationType) {
        return modelMapper.map(this, destinationType);
    }

    public <D> D toDto(ModelMapper modelMapper, D destinationType) {
        modelMapper.map(this, destinationType);
        return destinationType;
    }

}
