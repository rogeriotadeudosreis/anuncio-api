package br.com.gral.anuncio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gral.anuncio.entity.TipoEmpreendimento;

@Repository
public interface TipoEmpreendimentoRepository extends JpaRepository<TipoEmpreendimento, Long>{

	TipoEmpreendimento findByNome(String nome);

}
