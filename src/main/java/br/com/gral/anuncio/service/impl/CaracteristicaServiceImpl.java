package br.com.gral.anuncio.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.gral.anuncio.entity.Caracteristica;
import br.com.gral.anuncio.exception.RecursoJaExisteException;
import br.com.gral.anuncio.exception.RecursoNaoEncontradoException;
import br.com.gral.anuncio.exception.RequisicaoComErroException;
import br.com.gral.anuncio.repository.CaracteristicaRepository;
import br.com.gral.anuncio.service.CaracteristicaService;
import br.com.gral.anuncio.service.dto.CaracteristicaDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class CaracteristicaServiceImpl implements CaracteristicaService {

	private final CaracteristicaRepository repository;

	private final ModelMapper mapper;

	public CaracteristicaDto salvar(final CaracteristicaDto caracteristicaDto) {
		log.info("Salvando...");

		CaracteristicaDto caracteristicaExistente = buscarPorNome(caracteristicaDto.getNome());

		if (!ObjectUtils.isEmpty(caracteristicaExistente))
			throw new RecursoJaExisteException("");

		Caracteristica caracteristicaSalvar = mapper.map(caracteristicaDto, Caracteristica.class);

		Caracteristica caracteristicaSalva = repository.save(caracteristicaSalvar);

		return mapper.map(caracteristicaSalva, CaracteristicaDto.class);
	}

	public CaracteristicaDto buscarPorNome(String nome) {
		log.info("Buscando pelo nome...");

		if (ObjectUtils.isEmpty(nome))
			throw new RequisicaoComErroException("Id nulo");

		Caracteristica caracteristica = repository.findByNome(nome);

		if (ObjectUtils.isEmpty(caracteristica)) {
			return null;
		}

		return mapper.map(caracteristica, CaracteristicaDto.class);
	}

	public void verificaSeExite(final Long id) {

		log.info("Verificando existencia...");

		if (ObjectUtils.isEmpty(id))
			throw new RequisicaoComErroException("Id nulo");

		Optional<Caracteristica> caracteristicaOptional = repository.findById(id);

		if (!caracteristicaOptional.isPresent())
			throw new RecursoNaoEncontradoException(id.toString());

	}

	public CaracteristicaDto buscarPorId(final Long id) {

		log.info("Bucando por id...");

		if (ObjectUtils.isEmpty(id))
			throw new RequisicaoComErroException("Id nulo");

		Optional<Caracteristica> caracteristicaOptional = repository.findById(id);

		Caracteristica caracteristicaBanco = caracteristicaOptional
				.orElseThrow(() -> new RecursoNaoEncontradoException(id.toString()));

		return mapper.map(caracteristicaBanco, CaracteristicaDto.class);
	}

	public void atualizar(CaracteristicaDto dto) {

		log.info("Atualizando...");

		this.verificaSeExite(dto.getId());

		Caracteristica caracteristicaSalvar = mapper.map(dto, Caracteristica.class);

		repository.save(caracteristicaSalvar);

	}

	public void excluir(Long id) {

		CaracteristicaDto dto = this.buscarPorId(id);

		verificaSeExite(dto.getId());

		Caracteristica caracteristicaSalvar = mapper.map(dto, Caracteristica.class);

		repository.delete(caracteristicaSalvar);
	}

	@Override
	public List<CaracteristicaDto> buscarCaracteristicas() {

		List<Caracteristica> list = repository.findAll(Sort.by(Sort.Direction.ASC, "nome"));

		if (list.isEmpty()) {
			throw new RecursoNaoEncontradoException("Lista de características vazia");
		}

		return list.stream().map(x -> new CaracteristicaDto(x)).collect(Collectors.toList());
	}

	@Override
	public List<Caracteristica> BuscarPorNomeLike(String nome) {
		List<Caracteristica> lista = repository.findByNameLike(nome);

		if (lista.isEmpty()) {
			throw new RecursoNaoEncontradoException("Registro procurado por nome não encontrado.");
		}
		return lista;
	}

}
