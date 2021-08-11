package br.com.gral.anuncio.service;

import java.util.List;

import br.com.gral.anuncio.service.dto.TipoImovelDto;

public interface TipoImovelService {
	
	public List<TipoImovelDto> buscarTodosTipoImovel();

	TipoImovelDto buscarPorId(final Long id);

	TipoImovelDto buscarPorNome(final String name);

	TipoImovelDto salvar(final TipoImovelDto tipoImovelDto);

	void atualizar(final TipoImovelDto tipoImovelDto); 

	void verificaSeExite(final Long id);

	void excluir(final Long id);
	
}
