package br.com.gral.anuncio.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

import br.com.gral.anuncio.entity.TipoImovel;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
public class TipoImovelDto implements Serializable {

	public TipoImovelDto(TipoImovel tipoImovel) {

		this.id = tipoImovel.getId();
		this.nome = tipoImovel.getNome();

	}

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "Nome é obrigatório")
	private String nome;

}
