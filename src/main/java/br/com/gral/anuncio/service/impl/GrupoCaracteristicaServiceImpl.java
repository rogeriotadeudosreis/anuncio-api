package br.com.gral.anuncio.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.gral.anuncio.entity.GrupoCaracteristica;
import br.com.gral.anuncio.exception.RecursoJaExisteException;
import br.com.gral.anuncio.exception.RecursoNaoEncontradoException;
import br.com.gral.anuncio.exception.RequisicaoComErroException;
import br.com.gral.anuncio.repository.GrupoCaracteristicaRepository;
import br.com.gral.anuncio.service.GrupoCaracteristicaService;
import br.com.gral.anuncio.service.dto.GrupoCaracteristicaDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class GrupoCaracteristicaServiceImpl implements GrupoCaracteristicaService {

	private final GrupoCaracteristicaRepository repository;

	private final ModelMapper mapper;

	@Override
	public GrupoCaracteristicaDto salvar(final GrupoCaracteristicaDto grupoCaracteristicaDto) {

		log.info("Salvando...");

		GrupoCaracteristica grupoCaracteristicaExistente = BuscarPorNome(grupoCaracteristicaDto.getNome());

		if (!ObjectUtils.isEmpty(grupoCaracteristicaExistente))
			throw new RecursoJaExisteException("");

		GrupoCaracteristica grupoCaracteristicaSalvar = mapper.map(grupoCaracteristicaDto, GrupoCaracteristica.class);

		GrupoCaracteristica grupoCaracteristicaSalvo = repository.save(grupoCaracteristicaSalvar);

		return mapper.map(grupoCaracteristicaSalvo, GrupoCaracteristicaDto.class);

	}

	private GrupoCaracteristica BuscarPorNome(String nome) {
			
			log.info("Buscando pelo nome...");

			if (ObjectUtils.isEmpty(nome)) throw new RequisicaoComErroException("Campo nulo");

			GrupoCaracteristica tipoImovel = repository.findByNome(nome);
			
			if (ObjectUtils.isEmpty(tipoImovel)) {
				return null;
			}
			
			return mapper.map(tipoImovel, GrupoCaracteristica.class);
		}
	

	@Override
	public void verificaSeExite(final Long id) {

		log.info("Verificando existencia...");

		if (ObjectUtils.isEmpty(id))
			throw new RequisicaoComErroException("Id nulo");

		Optional<GrupoCaracteristica> grupoCaracteristicaOptional = repository.findById(id);

		if (!grupoCaracteristicaOptional.isPresent())
			throw new RecursoNaoEncontradoException(id.toString());

	}

	public GrupoCaracteristicaDto buscarPorId(final Long id) {

		log.info("Bucando por id...");

		if (ObjectUtils.isEmpty(id))
			throw new RequisicaoComErroException("Id nulo");

		Optional<GrupoCaracteristica> grupoCaracteristicaOptional = repository.findById(id);

		GrupoCaracteristica grupoCaracteristicaBanco = grupoCaracteristicaOptional
				.orElseThrow(() -> new RecursoNaoEncontradoException(id.toString()));

		return mapper.map(grupoCaracteristicaBanco, GrupoCaracteristicaDto.class);
	}

	@Override
	public void atualizar(GrupoCaracteristicaDto dto) {

		log.info("Atualizando...");

		this.verificaSeExite(dto.getId());

		GrupoCaracteristica grupoCaracteristicaSalvar = mapper.map(dto, GrupoCaracteristica.class);

		repository.save(grupoCaracteristicaSalvar);

	}

	@Override
	public void excluir(Long id) {

		GrupoCaracteristicaDto dto = this.buscarPorId(id);

		verificaSeExite(dto.getId());

		GrupoCaracteristica grupoCaracteristicaSalvar = mapper.map(dto, GrupoCaracteristica.class);

		repository.delete(grupoCaracteristicaSalvar);
	}

	public List<GrupoCaracteristicaDto> buscarTodosGrupoCaracteristica() {

		List<GrupoCaracteristica> list = repository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
		
		if (list.isEmpty()) {
			throw new RecursoNaoEncontradoException("Lista de grupo de características vazia");
		}

		return list.stream().map(x -> new GrupoCaracteristicaDto(x)).collect(Collectors.toList());
	}

	@Override
	public List<GrupoCaracteristica> findByNomeLike(String nome) {
		List<GrupoCaracteristica> lista = repository.findByNomeLike(nome);
		
		if (lista.isEmpty()) {
			throw new RecursoNaoEncontradoException("Registro procurado por nome não encontrado.");
		}
		return lista;
	}
	
	

}
