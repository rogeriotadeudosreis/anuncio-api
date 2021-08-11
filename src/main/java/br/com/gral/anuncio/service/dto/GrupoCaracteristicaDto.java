package br.com.gral.anuncio.service.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;

import br.com.gral.anuncio.entity.Caracteristica;
import br.com.gral.anuncio.entity.GrupoCaracteristica;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
public class GrupoCaracteristicaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "O nome do grupo de característica deve ser informado.")
	private String nome;

	private List<Caracteristica> caracteristica;

	public GrupoCaracteristicaDto(GrupoCaracteristica grupocaracteristica) {
		super();
		this.id = grupocaracteristica.getId();
		this.nome = grupocaracteristica.getNome();
		this.caracteristica = grupocaracteristica.getCaracteristica();
	}

}
