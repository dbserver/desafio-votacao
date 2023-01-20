package br.com.vitt.apivotacao.entities.enums;

public enum Status {
		UNABLE_TO_VOTE (0, "Não pode votar"),
		ABLE_TO_VOTE (1,"Pode votar");
		
		private Integer cod;
		private String status;
		
		private Status(Integer cod, String status) {
			this.cod = cod;
			this.status = status;
		}

		public Integer getCod() {
			return cod;
		}

		public String getStatus() {
			return status;
		}
		
		public static Status toEnum(Integer cod) {			
			if(cod == null) {
				return null;
			}
			for(Status x : Status.values()){
				if(cod.equals(x.getCod())) {
					return x;
				}
			}
			throw new IllegalArgumentException("Código inválido: " + cod );			
		}
}
