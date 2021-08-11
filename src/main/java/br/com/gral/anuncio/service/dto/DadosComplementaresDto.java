package br.com.gral.anuncio.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import br.com.gral.anuncio.entity.DadosComplementares;
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
public class DadosComplementaresDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "O nome do empreendimento deve ser informado.")
	private String nomeDoEmpreendimento;
	@Digits(integer = 8, fraction = 2, message = "Valor do imóvel deve ser preenchido com digitos.")	
	private BigDecimal valor;
	@Digits(integer = 4, fraction = 2, message = "Informe a metragem correta do imóvel.")
	private float metrosQuadrados;
	private Integer qtdQuartos;
	private Integer qtdSuites;
	private Integer qtdBanheiros;
	private Integer qtdVagas;

	public DadosComplementaresDto(DadosComplementares dadosComplementares) {
		super();
		this.id = dadosComplementares.getId();
		this.nomeDoEmpreendimento = dadosComplementares.getNomeEmpreendimento();
		this.valor = dadosComplementares.getValor();
		this.metrosQuadrados = dadosComplementares.getMetrosQuadrados();
		this.qtdQuartos = dadosComplementares.getQtdQuartos();
		this.qtdSuites = dadosComplementares.getQtdSuites();
		this.qtdBanheiros = dadosComplementares.getQtdBanheiros();
		this.qtdVagas = dadosComplementares.getQtdVagas();

	}

}
