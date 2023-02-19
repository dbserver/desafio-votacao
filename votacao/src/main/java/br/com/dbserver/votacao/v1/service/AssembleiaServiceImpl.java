package br.com.dbserver.votacao.v1.service;

import br.com.dbserver.votacao.v1.dto.request.AssembleiaRequest;
import br.com.dbserver.votacao.v1.dto.response.AssembleiaResponse;
import br.com.dbserver.votacao.v1.entity.Assembleia;
import br.com.dbserver.votacao.v1.exception.ValidationException;
import br.com.dbserver.votacao.v1.exception.NotFoundException;
import br.com.dbserver.votacao.v1.mapper.MapperAssembleia;
import br.com.dbserver.votacao.v1.repository.AssembleiaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Log4j2
@AllArgsConstructor
@Service
public class AssembleiaServiceImpl implements AssembleiaService {

	private final AssembleiaRepository assembleiaRepository;

	@Override
	public AssembleiaResponse criar(AssembleiaRequest assembleiaRequest) {
		log.info("Metodo: criar");

		validarData(assembleiaRequest.getInicio(), assembleiaRequest.getFim());

		Assembleia assembleia =
				MapperAssembleia.INSTANCE.requestToAssembleia(assembleiaRequest);

		Assembleia assembleiaSalva = salvar(assembleia);

		return MapperAssembleia.INSTANCE.assembleiaToResponse(assembleiaSalva);
	}

	protected Assembleia buscarPorID(Long id) {
		return assembleiaRepository.findById(id).orElseThrow(() ->
				new NotFoundException("Assembleia não encontrada!"));
	}

	protected Assembleia salvar(Assembleia assembleia) {

		return assembleiaRepository.save(assembleia);
	}

	private void validarData(LocalDateTime inicio, LocalDateTime fim) {
		if (fim.isBefore(inicio))
			throw new ValidationException("Data inicio não pode ser superior a data fim");
	}
}

