package br.com.dbserver.voting.services.impl;

import br.com.dbserver.voting.converters.associate.AssociateDtoToAssociateMapper;
import br.com.dbserver.voting.dtos.AssociateDTO;
import br.com.dbserver.voting.exceptions.InvalidCpfException;
import br.com.dbserver.voting.helpers.Util;
import br.com.dbserver.voting.models.Associate;
import br.com.dbserver.voting.repositories.AssociateRepository;
import br.com.dbserver.voting.services.AssociateService;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssociateServiceImpl implements AssociateService {

    private final AssociateRepository associateRepository;
    private final AssociateDtoToAssociateMapper associateDtoToAssociateMapper;

    public AssociateServiceImpl(AssociateRepository associateRepository, AssociateDtoToAssociateMapper associateDtoToAssociateMapper) {
        this.associateRepository = associateRepository;
        this.associateDtoToAssociateMapper = associateDtoToAssociateMapper;
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "associate", allEntries = true),
            @CacheEvict(value = {"associates"}, allEntries = true)
    })
    public void createAssociate(AssociateDTO associateDTO) {

        if(!Util.validCpf(associateDTO.cpf())){
            throw new InvalidCpfException("CPF invalido");
        }

        Associate associate = associateDtoToAssociateMapper.map(associateDTO, new Associate());
        associateRepository.save(associate);
    }

    @Override
    @Cacheable(value = "associates")
    public Page<AssociateDTO> listAll(Pageable pageable) {
        Page<Associate> associatePage = associateRepository.findAll(pageable);

        List<AssociateDTO> associates = associatePage
                .stream()
                .map(associate -> new AssociateDTO(associate.getId(), associate.getName(), associate.getCpf()))
                .collect(Collectors.toList());

        return new PageImpl<>(associates, pageable, associatePage.getTotalPages());
    }
}
