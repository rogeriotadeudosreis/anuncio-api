package br.com.gral.anuncio.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.ToString;

@ToString
@Entity(name="Anuncio")
@Table(name = "tb_anuncio")
public class Anuncio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String categoria;
	@Column(nullable = false)
	private String descricao;
	@Column(name = "data_cadastro", nullable = true)
	private LocalDate dataCadastro;
	@Column(name = "quant_visualizacoes", nullable = true)
	private Integer qtdDeVisualizacoes;
	@Column(nullable = false)
	private boolean ativo;	
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "tipoImovel_id", nullable = true)
	private TipoImovel tipoImovel;

	@ManyToOne
	@JoinColumn(name = "tipoEmpreendimento_id", nullable = true)
	private TipoEmpreendimento tipoEmpreendimento;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dadosComplementares_id", nullable = true)
	private DadosComplementares dadosComplementares;

	@OneToOne(cascade =CascadeType.ALL)
	@JoinColumn(name = "endereco_id", nullable = true)
	private Endereco endereco;

	@ManyToMany
	@JoinTable(name = "tb_anuncio_caracteristica",
	joinColumns = @JoinColumn(name = "anuncio_id"), 
	inverseJoinColumns = @JoinColumn(name = "caracteristica_id"))
	private List<Caracteristica> caracteristicas = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "anuncio_id", nullable = false)
	private List<Imagem> imagem = new ArrayList<>();

	@PrePersist
	public void onSave() {
		this.dataCadastro = LocalDate.now();
	}
	
	public void addCaracteristica(Caracteristica caracteristica) {
		caracteristicas.add(caracteristica);
	}
	
	public void removeCaracteristica(Caracteristica caracteristica) {
		caracteristicas.remove(caracteristica);
    }
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Integer getQtdDeVisualizacoes() {
		return qtdDeVisualizacoes;
	}

	public void setQtdDeVisualizacoes(Integer qtdDeVisualizacoes) {
		this.qtdDeVisualizacoes = qtdDeVisualizacoes;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoImovel getTipoImovel() {
		return tipoImovel;
	}

	public void setTipoImovel(TipoImovel tipoImovel) {
		this.tipoImovel = tipoImovel;
	}

	public TipoEmpreendimento getTipoEmpreendimento() {
		return tipoEmpreendimento;
	}

	public void setTipoEmpreendimento(TipoEmpreendimento tipoEmpreendimento) {
		this.tipoEmpreendimento = tipoEmpreendimento;
	}

	public DadosComplementares getDadosComplementares() {
		return dadosComplementares;
	}

	public void setDadosComplementares(DadosComplementares dadosComplementares) {
		this.dadosComplementares = dadosComplementares;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public List<Imagem> getImagem() {
		return imagem;
	}

	public void setImagem(List<Imagem> imagem) {
		this.imagem = imagem;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Anuncio)) {
			return false;
		}
		Anuncio other = (Anuncio) obj;
		return Objects.equals(id, other.id);
	}
    
    
	

}
