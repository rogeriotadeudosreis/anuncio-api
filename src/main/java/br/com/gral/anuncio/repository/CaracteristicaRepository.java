package br.com.gral.anuncio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gral.anuncio.entity.Caracteristica;

@Repository
public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Long> {
	
	@Query("select a from Caracteristica a where a.nome like %:#{[0]}% and a.nome like %:nome%")
	List<Caracteristica> findByNameLike (String nome);

	Caracteristica findByNome(String name);
	
}
