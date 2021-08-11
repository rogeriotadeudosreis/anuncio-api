package br.com.gral.anuncio.service.impl;

import br.com.gral.anuncio.entity.Usuario;
import br.com.gral.anuncio.enumeration.EnumPerfil;
import br.com.gral.anuncio.exception.LoginException;
import br.com.gral.anuncio.exception.RecursoJaExisteException;
import br.com.gral.anuncio.repository.UsuarioRepository;
import br.com.gral.anuncio.service.UsuarioService;
import br.com.gral.anuncio.service.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    private final ModelMapper mapper;

    @Override
    public UsuarioDto login(UsuarioDto dto) {

        log.info("Iniciando login usuário com email {}", dto.getEmail());

        Optional<Usuario> usuarioSalvo = repository.findByEmail(dto.getEmail());

        Usuario usuario = usuarioSalvo.orElseThrow(() -> new LoginException("Usuario Inexistente!"));

        if (!usuario.getEmail().equals(dto.getEmail()) && usuario.getSenha().equals(dto.getSenha())) {
            throw new LoginException("Usuario ou senha inválidos");
        }

        return mapper.map(usuario, UsuarioDto.class);
    }

    @Override
    public UsuarioDto salvar(UsuarioDto dto) {
        log.info("Iniciando salvar usuário com email {}", dto.getEmail());

        Optional<Usuario> usuarioSalvo = repository.findByEmail(dto.getEmail());

        if (usuarioSalvo.isPresent()) throw new RecursoJaExisteException(usuarioSalvo.get().getEmail());

        dto.setPerfil(EnumPerfil.VENDEDOR);

        Usuario usuario = mapper.map(dto, Usuario.class);

        Usuario usuarioNovo = repository.save(usuario);

        return mapper.map(usuarioNovo, UsuarioDto.class);
    }


}
