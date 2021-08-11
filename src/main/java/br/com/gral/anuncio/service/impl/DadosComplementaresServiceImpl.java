package br.com.gral.anuncio.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.gral.anuncio.entity.DadosComplementares;
import br.com.gral.anuncio.exception.RecursoNaoEncontradoException;
import br.com.gral.anuncio.exception.RequisicaoComErroException;
import br.com.gral.anuncio.repository.DadosComplementaresRepository;
import br.com.gral.anuncio.service.DadosComplementaresService;
import br.com.gral.anuncio.service.dto.DadosComplementaresDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class DadosComplementaresServiceImpl implements DadosComplementaresService {

	private final DadosComplementaresRepository repository;

	private final ModelMapper mapper;


	@Override
	public DadosComplementaresDto salvar(final DadosComplementaresDto dadosComplementaresDto) {

		log.info("Salvando...");
		
		DadosComplementares dadosComplementaresSalvar = mapper.map(dadosComplementaresDto, DadosComplementares.class);
		
		DadosComplementares dadosSalvos = repository.save(dadosComplementaresSalvar);

		return mapper.map(dadosSalvos, DadosComplementaresDto.class);

	}


	@Override
	public void verificaSeExite(final Long id) {

		log.info("Verificando existencia...");

		if (ObjectUtils.isEmpty(id)) throw new RequisicaoComErroException("Id nulo");

		Optional<DadosComplementares> dadosComplementaresOptional = repository.findById(id);
		
		if (!dadosComplementaresOptional.isPresent()) throw new RecursoNaoEncontradoException(id.toString());
		
	}
	
	public DadosComplementaresDto buscarPorId(final Long id) {

		log.info("Bucando por id...");

		if (ObjectUtils.isEmpty(id)) throw new RequisicaoComErroException("Id nulo");

		Optional<DadosComplementares> dadosComplementaresOptional = repository.findById(id);
		
		DadosComplementares dadosComplementaresBanco = dadosComplementaresOptional.orElseThrow(()-> new RecursoNaoEncontradoException(id.toString()));

		return mapper.map(dadosComplementaresBanco, DadosComplementaresDto.class);
	}


	@Override
	public void atualizar(DadosComplementaresDto dto) {
		
		log.info("Atualizando...");
		
		this.verificaSeExite(dto.getId());

		DadosComplementares dadosComplementaresSalvar = mapper.map(dto, DadosComplementares.class);

		repository.save(dadosComplementaresSalvar);
		
	}

	@Override
	public void excluir(Long id) {
		
		DadosComplementaresDto dto = this.buscarPorId(id);

		verificaSeExite(dto.getId());

		DadosComplementares dadosComplementaresSalvar = mapper.map(dto, DadosComplementares.class);
		
		repository.delete(dadosComplementaresSalvar);
	}
	
}
