package br.com.gral.anuncio.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_dados_complementares")
public class DadosComplementares {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	private String nomeEmpreendimento;
	@Column(nullable = false)	
	private BigDecimal valor;
	@Column(name = "met_quadrados", nullable = false)
	private float metrosQuadrados;
	@Column(name = "quant_quartos")
	private Integer qtdQuartos;
	@Column(name = "quant_suites")
	private Integer qtdSuites;
	@Column(name = "quant_banheiros")
	private Integer qtdBanheiros;
	@Column(name = "quant_vagas")
	private Integer qtdVagas;

}



