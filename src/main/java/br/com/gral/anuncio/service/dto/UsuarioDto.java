package br.com.gral.anuncio.service.dto;

import br.com.gral.anuncio.enumeration.EnumPerfil; 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
public class UsuarioDto {

    private Long id;

    @Size(max = 60 )
    private String nome;

    @Email
    private String email;

    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres.")
    private String senha;

    private String telefone;

    private EnumPerfil perfil;

    private LocalDate dtCadastro;

    private boolean ativo;
}
