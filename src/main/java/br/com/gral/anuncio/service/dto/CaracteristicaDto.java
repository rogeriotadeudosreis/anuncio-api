package br.com.gral.anuncio.service.dto;

import br.com.gral.anuncio.entity.Caracteristica;
import br.com.gral.anuncio.entity.GrupoCaracteristica;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
public class CaracteristicaDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message = "O nome da característica deve informado." )
	private String nome;
	@NotNull(message = "O grupo da característica deve ser informado.")
	private GrupoCaracteristica grupoCaracteristica;

	public CaracteristicaDto(Caracteristica caracteristica) {
		this.id = caracteristica.getId();
		this.nome = caracteristica.getNome();
		this.grupoCaracteristica = caracteristica.getGrupoCaracteristica();
	}

}
