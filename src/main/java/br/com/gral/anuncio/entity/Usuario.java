package br.com.gral.anuncio.entity;

import br.com.gral.anuncio.enumeration.EnumPerfil;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	private String nome;

	@Email
	@NotEmpty(message = "Campo email não pode ser nulo ou vazio.")
	private String email;

	@Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres.")
	private String senha;

	private String telefone;

	@Enumerated(EnumType.STRING)
	private EnumPerfil perfil;

	private LocalDateTime dataCadastro;

	private LocalDateTime dataAtualizacao;

	private boolean ativo;

	@PrePersist
	public void onSave() {
		this.ativo = true;
		this.dataCadastro = LocalDateTime.now();
	}

	@PreUpdate
	public void onUpdate() {
		this.dataAtualizacao = LocalDateTime.now();
	}

}
