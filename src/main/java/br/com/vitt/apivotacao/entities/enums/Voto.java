package br.com.vitt.apivotacao.entities.enums;

public enum Voto {
		SIM (1,"Sim"),
		NAO (0, "Não"),
		SEM_VOTO(2,"Sem Voto");
		
		
		private Integer cod;
		private String decisaoVoto;
		
		private Voto(Integer cod, String decisaoVoto) {
			this.cod = cod;
			this.decisaoVoto = decisaoVoto;
		}

		public Integer getCod() {
			return cod;
		}

		public String getDecisaoVoto() {
			return decisaoVoto;
		}
		
		public static Voto toEnum(Integer cod) {			
			if(cod == null) {
				return null;
			}
			for(Voto x : Voto.values()){
				if(cod.equals(x.getCod())) {
					return x;
				}
			}
			throw new IllegalArgumentException("Código inválido: " + cod );			
		}
			
}
