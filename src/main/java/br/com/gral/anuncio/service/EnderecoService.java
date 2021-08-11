package br.com.gral.anuncio.service;

import br.com.gral.anuncio.service.dto.EnderecoDto;

public interface EnderecoService {

	EnderecoDto buscarPorId(final Long id);

	EnderecoDto salvar(final EnderecoDto enderecoDto);

	void atualizar(final EnderecoDto enderecoDto); 
	
	void verificaSeExite(final Long id);

	void excluir(final Long id);
	
}
