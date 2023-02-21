package br.com.dbserver.votacao.v1.service;

import br.com.dbserver.votacao.v1.dto.request.PautaRequest;
import br.com.dbserver.votacao.v1.dto.response.PautaResponse;
import br.com.dbserver.votacao.v1.dto.response.PautaResultadoResponse;
import br.com.dbserver.votacao.v1.entity.Assembleia;
import br.com.dbserver.votacao.v1.entity.Pauta;
import br.com.dbserver.votacao.v1.enums.VotoEnum;
import br.com.dbserver.votacao.v1.exception.NotFoundException;
import br.com.dbserver.votacao.v1.exception.ValidationException;
import br.com.dbserver.votacao.v1.mapper.MapperGererics;
import br.com.dbserver.votacao.v1.mapper.MapperPauta;
import br.com.dbserver.votacao.v1.mapper.Resposta;
import br.com.dbserver.votacao.v1.repository.PautaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Log4j2
@AllArgsConstructor
@Service
public class PautaServiceImpl implements PautaService {
	private final PautaRepository pautaRepository;
	private final AssembleiaServiceImpl assembleiaService;

	protected Pauta buscarPorId(Long id) {
		log.info("Metodo: buscarPorId - ID: " + id);
		return pautaRepository.findById(id).orElseThrow(
				() -> new NotFoundException("ID não encontrado!")
		);
	}

	@Transactional
	@Override
	public PautaResponse criarPauta(PautaRequest pautaRequest) {
		log.info("Metodo: criarPauta - Assembeleia ID: " + pautaRequest.getAssembleiaId());

		validardata(pautaRequest.getInicio(), pautaRequest.getFim());

		Assembleia assembleia = assembleiaService.buscarPorID(pautaRequest.getAssembleiaId());
		validardata(assembleia.getInicio(), assembleia.getFim());

		Pauta pauta = MapperPauta.INSTANCE.requestToPauta(pautaRequest);
		Pauta pautaSalva = salvar(pauta);
		assembleia.getPautas().add(pauta);

		assembleiaService.salvar(assembleia);

		return MapperPauta.INSTANCE.pautaToResponse(pautaSalva);
	}

	@Override
	public PautaResultadoResponse buscarPorID(Long id) {
		Pauta pauta = buscarPorId(id);
		PautaResultadoResponse pautaResponse = MapperPauta.INSTANCE.pautaToPautaResultadoResponse(pauta);

		pautaResponse.setVotoNao(pauta.getVotos().stream().filter(voto -> voto.getValor().equals(VotoEnum.SIM)).count());
		pautaResponse.setVotoSim(pauta.getVotos().stream().filter(voto -> voto.getValor().equals(VotoEnum.NAO)).count());

		return pautaResponse;
	}

	@Override
	public Resposta<PautaResponse> buscarTodas(Pageable pageable) {
		Page<Pauta> pautaPage = pautaRepository.findAll(pageable);

		MapperGererics<Pauta, PautaResponse> mapper = new MapperGererics<>();

		return mapper.toPagina(pautaPage, MapperPauta.INSTANCE::pautaToResponse);
	}

	protected Pauta salvar(Pauta pauta) {
		log.info("Metodo: salvar - Descricao da Pauta: " + pauta.getDescricao());
		return pautaRepository.save(pauta);
	}

	private void validardata(LocalDateTime inicio, LocalDateTime fim) {
		if (fim.isBefore(inicio) || inicio.isBefore(now())) {
			throw new ValidationException("Datas inválidas!");
		}
	}
}