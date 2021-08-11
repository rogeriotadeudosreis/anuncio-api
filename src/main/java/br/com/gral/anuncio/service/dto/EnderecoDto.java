package br.com.gral.anuncio.service.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.gral.anuncio.entity.Endereco;
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
public class EnderecoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "O logradouro deve ser informado.")
	private String logradouro;
	@NotBlank(message = "O número deve ser informado.")
	private String numero;
	private String complemento;
	@NotBlank(message = "O bairro deve ser informado.")
	private String bairro;
	@NotBlank(message = "A cidade deve ser informada.")
	private String localidade;
	@NotBlank(message = "O CEP deve ser informado.")
	@Size(min=8, max=8)
	private String cep;
	@NotBlank(message = "A UF (Estado) deve ser informada.")
	private String uf;
	
	private String ddd;
	private String gia;
	private String ibge;
	private String siafi;

	public EnderecoDto (Endereco endereco) {
		super();
		this.id = endereco.getId();
		this.logradouro = endereco.getLogradouro();
		this.numero = endereco.getNumero();
		this.complemento = endereco.getComplemento();
		this.bairro = endereco.getBairro();
		this.localidade = endereco.getLocalidade();
		this.cep = endereco.getCep();
		this.uf = endereco.getUf();
		this.ddd = endereco.getDdd();
		this.gia = endereco.getGia();
		this.ibge = endereco.getIbge();
		this.siafi = endereco.getSiafi();
	}

}

/*
 * id SERIAL PRIMARY KEY, logradouro varchar(100) NOT NULL, numero varchar (5)
 * NOT NULL, complemento varchar(50), bairro varchar(50) NOT NULL, cidade
 * varchar(100) NOT NULL, cep varchar(8) NOT NULL, uf varchar(2) NOT NULL
 */
