package br.com.gral.anuncio.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.gral.anuncio.entity.TipoEmpreendimento;
import br.com.gral.anuncio.exception.RecursoJaExisteException;
import br.com.gral.anuncio.exception.RecursoNaoEncontradoException;
import br.com.gral.anuncio.exception.RequisicaoComErroException;
import br.com.gral.anuncio.repository.TipoEmpreendimentoRepository;
import br.com.gral.anuncio.service.TipoEmpreendimentoService;
import br.com.gral.anuncio.service.dto.TipoEmpreendimentoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class TipoEmpreendimentoServiceImpl implements TipoEmpreendimentoService {

	private final TipoEmpreendimentoRepository repository;

	private final ModelMapper mapper;


	@Override
	public TipoEmpreendimentoDto salvar(final TipoEmpreendimentoDto tipoEmpreendimentoDto) {

		log.info("Salvando...");
		
		TipoEmpreendimentoDto empreendimentoExistente = buscarPorNome(tipoEmpreendimentoDto.getNome());

		if (!ObjectUtils.isEmpty(empreendimentoExistente)) throw new RecursoJaExisteException("");

		TipoEmpreendimento tipoEmpreendimentoSalvar = mapper.map(tipoEmpreendimentoDto, TipoEmpreendimento.class);
		
		TipoEmpreendimento empreendimentoSalvo = repository.save(tipoEmpreendimentoSalvar);

		return mapper.map(empreendimentoSalvo, TipoEmpreendimentoDto.class);

	}

	@Override
	public TipoEmpreendimentoDto buscarPorNome(String nome) {
		
		log.info("Buscando pelo nome...");

		if (ObjectUtils.isEmpty(nome)) throw new RequisicaoComErroException("Id nulo");

		TipoEmpreendimento tipoEmpreendimento = repository.findByNome(nome);
		
		if (ObjectUtils.isEmpty(tipoEmpreendimento)) {
			return null;
		}
		
		return mapper.map(tipoEmpreendimento, TipoEmpreendimentoDto.class);
	}

	@Override
	public void verificaSeExite(final Long id) {

		log.info("Verificando existencia...");

		if (ObjectUtils.isEmpty(id)) throw new RequisicaoComErroException("Id nulo");

		Optional<TipoEmpreendimento> tipoEmpreendimentoOptional = repository.findById(id);
		
		if (!tipoEmpreendimentoOptional.isPresent()) throw new RecursoNaoEncontradoException(id.toString());
		
	}
	
	public TipoEmpreendimentoDto buscarPorId(final Long id) {

		log.info("Bucando por id...");

		if (ObjectUtils.isEmpty(id)) throw new RequisicaoComErroException("Id nulo");

		Optional<TipoEmpreendimento> tipoEmpreendimentoOptional = repository.findById(id);
		
		TipoEmpreendimento tipoEmpreendimentoBanco = tipoEmpreendimentoOptional.orElseThrow(()-> new RecursoNaoEncontradoException(id.toString()));

		return mapper.map(tipoEmpreendimentoBanco, TipoEmpreendimentoDto.class);
	}


	@Override
	public void atualizar(TipoEmpreendimentoDto dto) {
		
		log.info("Atualizando...");
		
		this.verificaSeExite(dto.getId());

		TipoEmpreendimento tipoEmpreendimentoSalvar = mapper.map(dto, TipoEmpreendimento.class);

		repository.save(tipoEmpreendimentoSalvar);
		
	}

	@Override
	public void excluir(Long id) {
		
		TipoEmpreendimentoDto dto = this.buscarPorId(id);

		verificaSeExite(dto.getId());

		TipoEmpreendimento tipoEmpreendimentoSalvar = mapper.map(dto, TipoEmpreendimento.class);
		
		repository.delete(tipoEmpreendimentoSalvar);
	}
	
	public List<TipoEmpreendimentoDto> buscarTodosTipoEmpreendimento() {

		List<TipoEmpreendimento> list = repository.findAll(Sort.by(Sort.Direction.ASC, "nome"));

		if (list.isEmpty()) {
			throw new RecursoNaoEncontradoException("Lista de tipo de empreendimento vazia");
		}

		return list.stream().map(x -> new TipoEmpreendimentoDto(x)).collect(Collectors.toList());
	}
	
}
