package com.desafio.votacao.dto.response;

import lombok.Data;

@Data
public class MessageResponse {

	private String message;

	public MessageResponse(String string) {
		this.message = string;
	}
}
