package br.com.gral.anuncio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gral.anuncio.entity.GrupoCaracteristica;

@Repository
public interface GrupoCaracteristicaRepository extends JpaRepository<GrupoCaracteristica, Long> {

	@Query("select a from GrupoCaracteristica a where a.nome like %:#{[0]}% and a.nome like %:nome%")
	List<GrupoCaracteristica> findByNomeLike(String nome);

	GrupoCaracteristica findByNome(String nome);

}
