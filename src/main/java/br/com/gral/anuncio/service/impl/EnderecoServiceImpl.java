package br.com.gral.anuncio.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.gral.anuncio.entity.Endereco;
import br.com.gral.anuncio.exception.RecursoNaoEncontradoException;
import br.com.gral.anuncio.exception.RequisicaoComErroException;
import br.com.gral.anuncio.repository.EnderecoRepository;
import br.com.gral.anuncio.service.EnderecoService;
import br.com.gral.anuncio.service.dto.EnderecoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class EnderecoServiceImpl implements EnderecoService {

	private final EnderecoRepository repository;

	private final ModelMapper mapper;


	@Override
	public EnderecoDto salvar(final EnderecoDto enderecoDto) {

		log.info("Salvando...");
		
		Endereco enderecoSalvar = mapper.map(enderecoDto, Endereco.class);
		
		Endereco enderecoSalvo = repository.save(enderecoSalvar);

		return mapper.map(enderecoSalvo, EnderecoDto.class);

	}
	
	@Override
	public void verificaSeExite(final Long id) {

		log.info("Verificando existencia...");

		if (ObjectUtils.isEmpty(id)) throw new RequisicaoComErroException("Id nulo");

		Optional<Endereco> enderecoOptional = repository.findById(id);
		
		if (!enderecoOptional.isPresent()) throw new RecursoNaoEncontradoException(id.toString());
		
	}
	
	public EnderecoDto buscarPorId(final Long id) {

		log.info("Bucando por id...");

		if (ObjectUtils.isEmpty(id)) throw new RequisicaoComErroException("Id nulo");

		Optional<Endereco> enderecoOptional = repository.findById(id);
		
		Endereco enderecoBanco = enderecoOptional.orElseThrow(()-> new RecursoNaoEncontradoException(id.toString()));

		return mapper.map(enderecoBanco, EnderecoDto.class);
	}


	@Override
	public void atualizar(EnderecoDto dto) {
		
		log.info("Atualizando...");
		
		this.verificaSeExite(dto.getId());

		Endereco enderecoSalvar = mapper.map(dto, Endereco.class);

		repository.save(enderecoSalvar);
		
	}

	@Override
	public void excluir(Long id) {
		
		EnderecoDto dto = this.buscarPorId(id);

		verificaSeExite(dto.getId());

		Endereco enderecoSalvar = mapper.map(dto, Endereco.class);
		
		repository.delete(enderecoSalvar);
	}
	
}
