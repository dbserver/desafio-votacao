package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.PautaDTO;
import br.tec.db.votacao.enums.PautaStatusEnum;
import br.tec.db.votacao.model.Assembleia;
import br.tec.db.votacao.model.Pauta;
import br.tec.db.votacao.repository.AssembleiaRepository;
import br.tec.db.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PautaServiceImpl implements PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private AssembleiaRepository assembleiaRepository;

    @Override
    public PautaDTO criarPauta(PautaDTO pautaDTO) {
        Assembleia assembleia = assembleiaRepository.findById(pautaDTO.idAssembleia()).orElseThrow();
        Pauta pauta = new Pauta();
        pauta.setTitulo(pautaDTO.titulo());
        pauta.setAssembleia(assembleia);
        pauta.setStatus(PautaStatusEnum.AGUARDANDO_VOTACAO);
        assembleia.getPautas().add(pauta);
        pautaRepository.save(pauta);
        return new PautaDTO(pauta);
    }

    @Override
    public PautaDTO buscarPautaPorId(Long id) {
        Pauta pauta = pautaRepository.findById(id).orElseThrow();
        return new PautaDTO(pauta);
    }

    @Override
    public List<PautaDTO> buscarTodasAsPautas() {
        return pautaRepository.findAll().stream().map(PautaDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<PautaDTO> buscarPautasPorAssembleia(Long id) {
        Assembleia assembleia = assembleiaRepository.findById(id).orElseThrow();
        return assembleia.getPautas().stream().map(PautaDTO::new).collect(Collectors.toList());
    }
}