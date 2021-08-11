package br.com.gral.anuncio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gral.anuncio.entity.Imagem;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Long> {

}
