package br.com.gral.anuncio.service;

import br.com.gral.anuncio.service.dto.DadosComplementaresDto;

public interface DadosComplementaresService {

	DadosComplementaresDto buscarPorId(final Long id);

	DadosComplementaresDto salvar(final DadosComplementaresDto dadosComplementaresDto);

	void atualizar(final DadosComplementaresDto dadosComplementaresDto); 

	void verificaSeExite(final Long id);

	void excluir(final Long id);
	
}
