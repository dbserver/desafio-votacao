package db.desafiovotacao.dto;

import db.desafiovotacao.model.AssociadoPauta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record AssociadoPautaRequest (
        @NotNull(message = "deve ser informada uma pauta")
        Long idPauta,

        @NotBlank(message = "cpf não pode estar em branco")
        @CPF(message = "cpf invalido")
        String cpf
) {
        public AssociadoPautaRequest(AssociadoPauta associadoPauta){
                this(associadoPauta.getPauta().getId(), associadoPauta.getAssociado().getCPF());
        }
}
