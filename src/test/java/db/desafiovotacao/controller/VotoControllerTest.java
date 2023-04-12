package db.desafiovotacao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import db.desafiovotacao.dto.ResultadoRequest;
import db.desafiovotacao.dto.ResultadoResponse;
import db.desafiovotacao.dto.VotoPautaRequest;
import db.desafiovotacao.dto.VotoRequest;
import db.desafiovotacao.enums.EnumVoto;
import db.desafiovotacao.exceptions.ConflictException;
import db.desafiovotacao.exceptions.NotFoundException;
import db.desafiovotacao.model.*;
import db.desafiovotacao.service.PautaService;
import db.desafiovotacao.service.VotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VotoPautaController.class)
class VotoControllerTest {

    @MockBean
    private VotoService votoService;

    @MockBean
    private PautaService pautaService;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private Pauta pauta;

    @Mock
    private Sessao sessao;

    @Mock
    private Voto voto;

    @Mock
    private Voto votoForaHorario;

    @Mock
    private VotoPauta votoPauta;

    @Mock
    private Associado associado;

    @Mock
    private Associado associadoNaoCadastradoParaVotar;

    @Mock
    private AssociadoPauta associadoPauta;

    @Captor
    ArgumentCaptor<AssociadoPauta> captor;

    public static final String PATH = "/voto";

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        associado = new Associado();
        pauta = new Pauta();
        sessao = new Sessao();
        voto = new Voto();
        votoForaHorario = new Voto();
        associadoNaoCadastradoParaVotar = new Associado();
        associadoPauta = new AssociadoPauta();
        votoPauta = new VotoPauta();

        associado.setCPF("036.397.700-72");
        associadoNaoCadastradoParaVotar.setCPF("810.068.660-27");

        sessao.setInicioSessao(LocalDateTime.now());
        sessao.setFinalSessao(sessao.getInicioSessao().plusMinutes(5));

        pauta.setId(1L);
        pauta.setTitulo("Pauta Teste");
        pauta.setDescricao("Teste");
        pauta.setDataCriacao(LocalDateTime.now());
        pauta.setSessao(sessao);

        voto.setVoto(EnumVoto.SIM);
        voto.setDataHoraVoto(sessao.getInicioSessao().plusMinutes(2));

        votoForaHorario.setVoto(EnumVoto.SIM);
        votoForaHorario.setDataHoraVoto(sessao.getInicioSessao().plusMinutes(10));

        votoPauta.setId(1L);
        votoPauta.setPauta(pauta);
        votoPauta.setVoto(voto);

        associadoPauta.setId(1L);
        associadoPauta.setVotou(true);
        associadoPauta.setPauta(pauta);
        associadoPauta.setAssociado(associado);
    }


    @Test
    void deveCadastrarVoto() throws Exception{

        Mockito.when(pautaService.buscarPautaPorID(Mockito.any())).thenReturn(pauta);

        Mockito.when(votoService.cadastrarVoto(Mockito.any(), Mockito.any())).thenReturn(votoPauta);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(new VotoPautaRequest(
                associado.getCPF(),
                pauta.getId(),
                new VotoRequest(voto))
        );

        this.mockMvc.perform(post(PATH)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void deveRetornarNotFoundExceptionCasoPautaNaoExista() throws Exception{

        Mockito.when(pautaService.buscarPautaPorID(Mockito.any())).thenThrow(NotFoundException.class);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(new VotoPautaRequest(
                associado.getCPF(),
                pauta.getId(),
                new VotoRequest(voto))
        );

        this.mockMvc.perform(post(PATH)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveRetornarNotFoundExceptionCasoAssociadoNaoEstejaCadastrado() throws Exception{

        Mockito.when(votoService.cadastrarVoto(Mockito.any(), Mockito.any())).thenThrow(NotFoundException.class);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(new VotoPautaRequest(
                associado.getCPF(),
                pauta.getId(),
                new VotoRequest(voto))
        );

        this.mockMvc.perform(post(PATH)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveRetornarConflictExceptionVotoEstejaForaDoHorario() throws Exception{

        Mockito.when(votoService.cadastrarVoto(Mockito.any(), Mockito.any())).thenThrow(ConflictException.class);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(new VotoPautaRequest(
                associado.getCPF(),
                pauta.getId(),
                new VotoRequest(votoForaHorario))
        );

        this.mockMvc.perform(post(PATH)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    void deveRetornarNotFoundExceptionSeAssociadoNaoCadastradoNaPauta() throws Exception{

        Mockito.when(votoService.cadastrarVoto(Mockito.any(), Mockito.any())).thenThrow(NotFoundException.class);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(new VotoPautaRequest(
                associadoNaoCadastradoParaVotar.getCPF(),
                pauta.getId(),
                new VotoRequest(votoForaHorario))
        );

        this.mockMvc.perform(post(PATH)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    void resultadoVotacao() throws Exception{

        ResultadoResponse resultado = new ResultadoResponse(0,0);

        Mockito.when(pautaService.buscarPautaPorID(Mockito.any())).thenReturn(pauta);
        Mockito.when(votoService.resultadoVotacao(Mockito.any())).thenReturn(resultado);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(new ResultadoRequest(pauta.getId()));

        this.mockMvc.perform(get(PATH)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deveRetornarNotFoundExceptionSePautaNaoExistir() throws Exception{

        Mockito.when(pautaService.buscarPautaPorID(Mockito.any())).thenThrow(NotFoundException.class);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(new ResultadoRequest(pauta.getId()));

        this.mockMvc.perform(get(PATH)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}