package br.com.dbserver.votacao.v1.service;

import br.com.dbserver.votacao.v1.client.CpfClientImpl;
import br.com.dbserver.votacao.v1.dto.request.AssociadoRequest;
import br.com.dbserver.votacao.v1.dto.response.AssociadoResponse;
import br.com.dbserver.votacao.v1.entity.Associado;
import br.com.dbserver.votacao.v1.exception.BadRequestException;
import br.com.dbserver.votacao.v1.exception.NotFoundException;
import br.com.dbserver.votacao.v1.mapper.MapperAssociado;
import br.com.dbserver.votacao.v1.repository.AssociadoRepository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Log4j2
@AllArgsConstructor
@Service
public class AssociadoServiceImpl implements AssociadoService {

	private final CpfClientImpl cpfClient;
	private final AssociadoRepository associadoRepository;

	@Override
	public AssociadoResponse salvar(AssociadoRequest associadoDto) throws BadRequestException {
		log.info("Metodo: salvar - Documento: " + associadoDto.getDocumento());
		cpfClient.validarCpf(associadoDto.getDocumento());
		try {
			Associado associado = MapperAssociado.INSTANCE.associadoRequesToAssociado(associadoDto);
			associadoRepository.save(associado);
			return MapperAssociado.INSTANCE.associadoToResponse(associado);
		} catch (DataAccessException e) {
			log.error("Documento: " + associadoDto.getDocumento() + " já foi cadastrado");
			throw new BadRequestException("Documento: " + associadoDto.getDocumento() + " já foi cadastrado ou é inválido");
		}
	}

	@Override
	public AssociadoResponse buscarPorCpfOuCnpj(String cpfOuCnpj) {
		log.info("Metodo: buscarPorDocumento - Documento: " + cpfOuCnpj);
		Associado associado = this.buscarPorDocumento(cpfOuCnpj);
		return MapperAssociado.INSTANCE.associadoToResponse(associado);
	}

	private Associado buscarPorDocumento(String documento) {
		log.info("Metodo: buscarPorDocumento - Documento: " + documento);
		return associadoRepository.findByDocumento(documento).orElseThrow(
				() -> new NotFoundException("Documento não encontrado")
		);
	}
}