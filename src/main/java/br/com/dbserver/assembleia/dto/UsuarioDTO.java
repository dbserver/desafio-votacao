package br.com.dbserver.assembleia.dto;

import br.com.dbserver.assembleia.entidade.enums.CpfStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
	
	private CpfStatusEnum status;

}
