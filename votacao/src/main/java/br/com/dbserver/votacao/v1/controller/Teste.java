package br.com.dbserver.votacao.v1.controller;

import br.com.dbserver.votacao.v1.entity.Assembleia;
import br.com.dbserver.votacao.v1.entity.Associado;
import br.com.dbserver.votacao.v1.entity.Pauta;
import br.com.dbserver.votacao.v1.entity.Voto;
import br.com.dbserver.votacao.v1.enums.StatusUsuarioEnum;
import br.com.dbserver.votacao.v1.enums.VotoEnum;
import br.com.dbserver.votacao.v1.repository.AssembleiaRepository;
import br.com.dbserver.votacao.v1.repository.AssociadoRepository;
import br.com.dbserver.votacao.v1.repository.PautaRepository;
import br.com.dbserver.votacao.v1.repository.VotoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/voto")
@RestController
public class Teste {
    private final AssembleiaRepository assembleiaRepository;
    private final AssociadoRepository associadoRepository;
    private final PautaRepository pautaRepository;
    private final VotoRepository votoRepository;

    public Teste(AssembleiaRepository assembleiaRepository,
                              AssociadoRepository associadoRepository,
                              PautaRepository pautaRepository, VotoRepository votoRepository) {
        this.assembleiaRepository = assembleiaRepository;
        this.associadoRepository = associadoRepository;
        this.pautaRepository = pautaRepository;
        this.votoRepository = votoRepository;
    }


    @RouterOperation(operation = @Operation(operationId = "Terste do gfuhr", summary = "sumario", tags = { "Cadatrar" },
            parameters = { @Parameter(in = ParameterIn.PATH, name = "id", description = "Employee Id") },
            responses = { @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid Employee ID supplied"),
                    @ApiResponse(responseCode = "404", description = "Employee not found") }))

    @PostMapping()
    public ResponseEntity<String> buscarTodos(){
        //Criar Associado
        Associado associado = Associado.builder()
                .nome("Anderson Fuhr")
                .cpf("00783419856")
                .status(StatusUsuarioEnum.NAO_PODE_VOTAR)
                .build();
        Associado associadoSalvo = associadoRepository.save(associado);

        //Criar uma Assembleia
       Assembleia assembleia = Assembleia.builder()
                .build();
        Assembleia assembleiaSalvo = assembleiaRepository.save(assembleia);

        //Criar uma Pauta e adicionar em uma Assembleia
        Pauta pauta = Pauta.builder()
                .descricao("Teste")
                .build();
        Pauta pautaSalvo = pautaRepository.save(pauta);

        assembleiaSalvo.getPautas().add(pautaSalvo);
        assembleiaRepository.save(assembleiaSalvo);

       //Criar voto e adicionar em uma pauta
        Voto voto = Voto.builder()
                .associado(associadoSalvo)
                .pauta(pautaSalvo)
                .valor(VotoEnum.SIM)
                .build();
        Voto votoSalvo = votoRepository.save(voto);
        pautaSalvo.getVotos().add(votoSalvo);
        pautaRepository.save(pautaSalvo);


        return new ResponseEntity<>("Executou o metodo", HttpStatus.OK);
    }
}
