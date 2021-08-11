package br.com.gral.anuncio.service;

import java.util.List;

import br.com.gral.anuncio.service.dto.TipoEmpreendimentoDto;

public interface TipoEmpreendimentoService {
	
	public List<TipoEmpreendimentoDto> buscarTodosTipoEmpreendimento();

	TipoEmpreendimentoDto buscarPorId(final Long id);

	TipoEmpreendimentoDto buscarPorNome(final String name);

	TipoEmpreendimentoDto salvar(final TipoEmpreendimentoDto tipoEmpreendimentoDto);

	void atualizar(final TipoEmpreendimentoDto tipoEmpreendimentoDto); 

	void verificaSeExite(final Long id);

	void excluir(final Long id);
	
}
