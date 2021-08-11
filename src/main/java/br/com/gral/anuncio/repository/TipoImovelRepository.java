package br.com.gral.anuncio.repository;

import br.com.gral.anuncio.entity.TipoImovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoImovelRepository extends JpaRepository<TipoImovel, Long> {

	TipoImovel findByNome(String name);
	
}
