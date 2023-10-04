package com.desafiovotacao.service.interfaces;


import com.desafiovotacao.dto.PautaDTO;

public interface IObterPautaPorIdAndResultado {

    PautaDTO buscar(String pautaId) throws Exception;

}
