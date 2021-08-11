package br.com.gral.anuncio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.gral.anuncio.entity.DadosComplementares;

@Repository
public interface DadosComplementaresRepository extends JpaRepository<DadosComplementares, Long> {

}
