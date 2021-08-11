package br.com.gral.anuncio.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "tb_caracteristica")
public class Caracteristica implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@Column(nullable = false, unique = true)
	private String nome;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "grupo_caracteristica_id")
	private GrupoCaracteristica grupoCaracteristica;

	public Caracteristica() {

	}

	public Caracteristica(String nome) {
		this.nome = nome;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Caracteristica caracteristica = (Caracteristica) o;
		return Objects.equals(nome, caracteristica.nome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

}
