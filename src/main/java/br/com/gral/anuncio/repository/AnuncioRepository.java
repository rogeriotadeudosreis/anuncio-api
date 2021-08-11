package br.com.gral.anuncio.repository;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gral.anuncio.entity.Anuncio;

@Repository
@JaversSpringDataAuditable
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

}
