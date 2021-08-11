package br.com.gral.anuncio.service.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import br.com.gral.anuncio.entity.TipoEmpreendimento;
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
public class TipoEmpreendimentoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "O nome do tipo de empreendimento deve ser informado.")
	private String nome;

	public TipoEmpreendimentoDto(TipoEmpreendimento tipoEmpreendimento) {
		super();
		this.id = tipoEmpreendimento.getId();
		this.nome = tipoEmpreendimento.getNome();

	}

}
