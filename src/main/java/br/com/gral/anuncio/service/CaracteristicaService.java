package br.com.gral.anuncio.service;

import java.util.List;

import br.com.gral.anuncio.entity.Caracteristica;
import br.com.gral.anuncio.service.dto.CaracteristicaDto;

public interface CaracteristicaService {
	
	List<Caracteristica> BuscarPorNomeLike(String nome);

	List<CaracteristicaDto> buscarCaracteristicas();
	
	CaracteristicaDto buscarPorId(final Long id);

	CaracteristicaDto buscarPorNome(final String name);

	CaracteristicaDto salvar(final CaracteristicaDto CaracteristicaDto);

	void atualizar(final CaracteristicaDto CaracteristicaDto); 

	void verificaSeExite(final Long id);

	void excluir(final Long id);

}
