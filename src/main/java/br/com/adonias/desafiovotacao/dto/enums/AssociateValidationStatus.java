package br.com.adonias.desafiovotacao.dto.enums;

public enum AssociateValidationStatus {
    ABLE_TO_VOTE("ABLE_TO_VOTE"),
    UNABLE_TO_VOTE("UNABLE_TO_VOTE");

    private String status;

    AssociateValidationStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}
