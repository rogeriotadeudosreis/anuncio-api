package br.com.gral.anuncio.service;

import java.util.List;

import br.com.gral.anuncio.entity.GrupoCaracteristica;
import br.com.gral.anuncio.service.dto.GrupoCaracteristicaDto;

public interface GrupoCaracteristicaService {
	
	public List<GrupoCaracteristicaDto> buscarTodosGrupoCaracteristica();

	GrupoCaracteristicaDto buscarPorId(final Long id);
	
	List<GrupoCaracteristica> findByNomeLike(String nome);

	GrupoCaracteristicaDto salvar(final GrupoCaracteristicaDto grupoCaracteristicaDto);

	void atualizar(final GrupoCaracteristicaDto grupoCaracteristicaDto); 

	void verificaSeExite(final Long id);

	void excluir(final Long id);
	
}
