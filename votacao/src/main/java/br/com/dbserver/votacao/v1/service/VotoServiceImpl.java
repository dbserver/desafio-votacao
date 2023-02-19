package br.com.dbserver.votacao.v1.service;

import br.com.dbserver.votacao.v1.dto.request.VotoRequest;
import br.com.dbserver.votacao.v1.dto.response.VotoResponse;
import br.com.dbserver.votacao.v1.entity.Associado;
import br.com.dbserver.votacao.v1.entity.Pauta;
import br.com.dbserver.votacao.v1.entity.Voto;
import br.com.dbserver.votacao.v1.enums.PautaStatusEnum;
import br.com.dbserver.votacao.v1.enums.StatusUsuarioEnum;
import br.com.dbserver.votacao.v1.exception.ValidationException;
import br.com.dbserver.votacao.v1.mapper.MapperVoto;
import br.com.dbserver.votacao.v1.repository.VotoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static java.time.LocalDateTime.now;

@Log4j2
@AllArgsConstructor
@Service
public class VotoServiceImpl implements VotoService {

	private final VotoRepository votoRepository;
	private final AssociadoServiceImpl associadoService;
	private final PautaServiceImpl pautaService;

	@Transactional
	@Override
	public VotoResponse salvar(VotoRequest votoRequest) {
		log.info("Metodo: salvar - Pauta: " + votoRequest.getPautaId() + " - Associado: " + votoRequest.getDocumentoAssociado());
		Associado associado = validaAssociado(votoRequest.getDocumentoAssociado());
		Pauta pauta = validaPauta(votoRequest.getPautaId());
		validaSeAssociadoJaVotouNaPauta(associado.getId(), pauta.getId());

		Voto voto = Voto.builder()
				.valor(votoRequest.getValor())
				.pauta(pauta)
				.associado(associado).build();

		votoRepository.save(voto);
		pauta.getVotos().add(voto);
		pautaService.salvar(pauta);

		return MapperVoto.INSTANCE.votoToResponse(voto);
	}

	private void validaSeAssociadoJaVotouNaPauta(long AssociadoId, long pautaId) {
	if(votoRepository.existsByAssociadoIdAndPautaId(AssociadoId, pautaId)){
		throw new ValidationException("Associado já votou nesta pauta!");
	}
	}

	private Associado validaAssociado(String documento) {
		Associado associado = associadoService.buscarPorDocumento(documento);
		if (associado.getStatus().equals(StatusUsuarioEnum.PODE_VOTAR)) {
			return associado;
		}
		throw new ValidationException("Associado : "+ documento +" - não pode votar!");
	}

	private Pauta validaPauta(Long id) {
		Pauta pauta = pautaService.buscarPorId(id);
		if (pauta.getStatus().equals(PautaStatusEnum.AGUARDANDO_RESULTADO)) {
			return pauta;
		}
		throw new ValidationException("Pauta de ID: "+ id +" - já expirou o tempo!");
	}
}
