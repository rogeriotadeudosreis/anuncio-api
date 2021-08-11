package br.com.gral.anuncio.service.impl;

 import br.com.gral.anuncio.entity.TipoImovel; 
import br.com.gral.anuncio.exception.RecursoJaExisteException;
import br.com.gral.anuncio.exception.RecursoNaoEncontradoException;
import br.com.gral.anuncio.exception.RequisicaoComErroException;
import br.com.gral.anuncio.repository.TipoImovelRepository;
import br.com.gral.anuncio.service.TipoImovelService;
import br.com.gral.anuncio.service.dto.TipoImovelDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class TipoImovelServiceImpl implements TipoImovelService {

	private final TipoImovelRepository repository;

	private final ModelMapper mapper;

	@Override
	public TipoImovelDto salvar(final TipoImovelDto tipoImovelDto) {

		log.info("Salvando...");
		
		TipoImovelDto imovelExistente = this.buscarPorNome(tipoImovelDto.getNome());

		if (!ObjectUtils.isEmpty(imovelExistente)) throw new RecursoJaExisteException("");

		TipoImovel tipoImovelSalvar = mapper.map(tipoImovelDto, TipoImovel.class);
		
		TipoImovel imovelSalvo = repository.save(tipoImovelSalvar);

		return mapper.map(imovelSalvo, TipoImovelDto.class);

	}

	@Override
	public TipoImovelDto buscarPorNome(String nome) {
		
		log.info("Buscando pelo nome...");

		if (ObjectUtils.isEmpty(nome)) throw new RequisicaoComErroException("Campo nulo");

		TipoImovel tipoImovel = repository.findByNome(nome);
		
		if (ObjectUtils.isEmpty(tipoImovel)) {
			return null;
		}
		
		return mapper.map(tipoImovel, TipoImovelDto.class);
	}

	@Override
	public void verificaSeExite(final Long id) {

		log.info("Verificando existencia...");

		if (ObjectUtils.isEmpty(id)) throw new RequisicaoComErroException("Id nulo");

		Optional<TipoImovel> tipoImovelOptional = repository.findById(id);
		
		if (!tipoImovelOptional.isPresent()) throw new RecursoNaoEncontradoException(id.toString());
		
	}
	
	public TipoImovelDto buscarPorId(final Long id) {
		log.info("Bucando por id...");

		if (ObjectUtils.isEmpty(id)) throw new RequisicaoComErroException("Id nulo");

		Optional<TipoImovel> tipoImovelOptional = repository.findById(id);
		
		TipoImovel tipoImovelBanco = tipoImovelOptional.orElseThrow(() -> new RecursoNaoEncontradoException(id.toString()));

		return mapper.map(tipoImovelBanco, TipoImovelDto.class);
	}


	@Override
	public void atualizar(TipoImovelDto dto) {
		
		log.info("Atualizando...");
		
		this.verificaSeExite(dto.getId());

		TipoImovel tipoImovelSalvar = mapper.map(dto, TipoImovel.class);

		repository.save(tipoImovelSalvar);
		
	}

	@Override
	public void excluir(Long id) {
		
		TipoImovelDto dto = this.buscarPorId(id);

		verificaSeExite(dto.getId());

		TipoImovel tipoImovelSalvar = mapper.map(dto, TipoImovel.class);
		
		repository.delete(tipoImovelSalvar);
	}
	
	public List<TipoImovelDto> buscarTodosTipoImovel() {

		List<TipoImovel> list = repository.findAll(Sort.by(Sort.Direction.ASC, "nome"));

		return list.stream()
				   .map(x -> new TipoImovelDto(x))
				   .collect(Collectors.toList());
	}
	
	
	
}
