package br.com.gral.anuncio.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class NegociacaoAnuncio {
	
	@Column(name = "data")
	private LocalDate data;
	
	@Column(name="Valor_negociacao")
	private BigDecimal valorDaNegociacao;

}
