
package br.com.gral.anuncio.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.gral.anuncio.entity.Anuncio;
import br.com.gral.anuncio.exception.RecursoNaoEncontradoException;
import br.com.gral.anuncio.exception.RequisicaoComErroException;
import br.com.gral.anuncio.repository.AnuncioRepository;

@Service
public class AnuncioService {

	@Autowired
	private AnuncioRepository anuncioRepository;

	public Anuncio salvar(final Anuncio anuncio) {

		validarAnuncio(anuncio);

		anuncioRepository.save(anuncio);

		return anuncio;
	}

	public void verificaSeExite(final Long id) {

		if (ObjectUtils.isEmpty(id))
			throw new RequisicaoComErroException("Id nulo");

		Optional<Anuncio> anuncioOptional = anuncioRepository.findById(id);

		if (!anuncioOptional.isPresent())
			throw new RecursoNaoEncontradoException(id.toString());

	}

	public Anuncio buscarPorId(final Long id) {

		if (ObjectUtils.isEmpty(id))
			throw new RequisicaoComErroException("Id nulo");

		Optional<Anuncio> anuncioOptional = anuncioRepository.findById(id);

		Anuncio anuncioBanco = anuncioOptional.orElseThrow(() -> new RecursoNaoEncontradoException(id.toString()));

		return anuncioBanco;
	}

	public void atualizar(Anuncio anuncio) {

		this.verificaSeExite(anuncio.getId());

		anuncioRepository.save(anuncio);

	}

	public void excluir(Long id) {

		Anuncio anuncio = this.buscarPorId(id);

		verificaSeExite(anuncio.getId());

		anuncioRepository.delete(anuncio);
	}

	public List<Anuncio> buscarTodosAnuncios() {

		List<Anuncio> list = anuncioRepository.findAll();

		if (list.isEmpty()) {
			throw new RecursoNaoEncontradoException("Lista de anúncios vazia");
		}

		return list;
	}

	public void validarAnuncio(Anuncio anuncio) {

		if (anuncio.getCategoria().isEmpty()) {
			throw new RequisicaoComErroException("A categoria deve ser informada");
		}

		if (anuncio.getDescricao().isEmpty()) {
			throw new RequisicaoComErroException("A descrição deve ser informada");
		}
		
		if (anuncio.getImagem().size() < 3) {
			throw new RequisicaoComErroException("Para cadastro de anúncio é necessário selecionar no mínimo 03 (três)"
					+ "imagens.");
		}
	}

}
