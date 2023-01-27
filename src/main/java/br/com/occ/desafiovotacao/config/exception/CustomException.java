package br.com.occ.desafiovotacao.config.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class CustomException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final HttpStatus status;

    public CustomException(String message, HttpStatus status) {
        this(message, status, null);
    }

    public CustomException(String message, HttpStatus status, Exception e) {
        super(message, e);
        this.status = status;
    }

    private static String criar(HttpStatus status, String mensagem) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"timestamp\": \"").append(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(OffsetDateTime.now())).append("\"");
        json.append(",\"message\":\"").append(mensagem == null ? ("") : (mensagem.replaceAll("\"", "\\\\\""))).append("\"");
        json.append(",\"status\":").append(status.value());


        json.append(",\"ok\":").append(isOk(status));
        json.append("}");

        return json.toString();
    }

    private static Boolean isOk(HttpStatus status) {
        return status.is1xxInformational() || status.is2xxSuccessful() || status.is3xxRedirection();
    }

    /**
     * @return the code
     */
    public HttpStatus getStatus() {
        return status;
    }

    public ResponseEntity<Object> getResponseEntity() {
        HttpStatus status = getStatus();

        String json = criar(getStatus(), super.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(json, headers, status);
    }
}
