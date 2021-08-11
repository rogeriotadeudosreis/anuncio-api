package br.com.gral.anuncio.service;

import br.com.gral.anuncio.service.dto.UsuarioDto;

public interface UsuarioService {

    UsuarioDto login(UsuarioDto dto);

    UsuarioDto salvar(UsuarioDto dto);
}
