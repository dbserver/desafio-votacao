package com.db.desafio.util.provider;

public class UrlProvider {
    public static final Long ID = 1L;
    public static final String URI_ASSOCIADO = ("/v1/associados");
    public static final String URI_PAUTA = ("/v1/pautas");
    public static final String URI_ASSOCIADO_ID = String.format("/v1/associados/%s",ID);
    public static final String URI_PAUTA_ID = String.format("/v1/pautas/%s",ID);
}
