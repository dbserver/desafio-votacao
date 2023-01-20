package br.com.vitt.apivotacao.entities.enums;

public enum StatusPauta {
		OPEN (1,"Em debate"),
		IN_VOTING (2, "Em votação"),
		APPROVED (3, "Aprovada"),
		REFUSED (4,"Recusada"),
		DRAW (5, "Empatou");
		
		private Integer cod;
		private String status;
		
		private StatusPauta(Integer cod, String status) {
			this.cod = cod;
			this.status = status;
		}

		public Integer getCod() {
			return cod;
		}

		public String getStatus() {
			return status;
		}
		
		public static StatusPauta toEnum(Integer cod) {			
			if(cod == null) {
				return null;
			}
			for(StatusPauta x : StatusPauta.values()){
				if(cod.equals(x.getCod())) {
					return x;
				}
			}
			throw new IllegalArgumentException("Código inválido: " + cod );			
		}
			
}
