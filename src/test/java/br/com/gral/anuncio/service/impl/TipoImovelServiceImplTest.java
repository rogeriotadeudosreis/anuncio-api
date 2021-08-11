package br.com.gral.anuncio.service.impl;

import br.com.gral.anuncio.entity.TipoImovel;
import br.com.gral.anuncio.exception.RecursoNaoEncontradoException;
import br.com.gral.anuncio.repository.TipoImovelRepository;
import br.com.gral.anuncio.service.dto.TipoImovelDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.math.BigInteger.ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TipoImovelServiceImplTest {

	@Spy
	@InjectMocks
	private TipoImovelServiceImpl service;
	
	@Mock
	private TipoImovelRepository repository;

	private Optional<TipoImovel> tipoImovel;

	@Mock
	private ModelMapper mapper;

	private TipoImovelDto tipoImovelDtoMock;

	@BeforeEach
	void setUp() {
		this.tipoImovel = Optional.of(new TipoImovel(ONE.longValue(),"Apartamento"));

		this.tipoImovelDtoMock = TipoImovelDto.builder()
				.id(1L)
				.nome("Apartamento")
				.build();
	}

	@Test
	void quandoSalvar_entaoRetornoNaoDeveSerNulo() {
		when(this.service.buscarPorNome("Apartamento")).thenReturn(null);

		when(this.mapper.map(tipoImovelDtoMock, TipoImovel.class)).thenReturn(this.tipoImovel.get());

		when(this.repository.save(this.tipoImovel.get())).thenReturn(this.tipoImovel.get());

		when(this.mapper.map(this.tipoImovel.get(), TipoImovelDto.class)).thenReturn(this.tipoImovelDtoMock);

		TipoImovelDto dto = this.service.salvar(this.tipoImovelDtoMock);

		assertThat(dto).isNotNull();
	}

	@Test
	void quandoBuscarPorId_entaoRetornoNaoDeveSerNulo() {
		when(this.repository.findById(1L)).thenReturn(this.tipoImovel);

		when(this.mapper.map(any(), any())).thenReturn(tipoImovelDtoMock);

		assertThat(this.service.buscarPorId(1L)).isNotNull();
	}

	@Test
	void quandoBuscarPorId_entaoDeveLancarRecursoNaoEncontradoException() {
		when(this.repository.findById(1L)).thenReturn(Optional.empty());

		assertThatExceptionOfType(RecursoNaoEncontradoException.class).isThrownBy(() -> this.service.buscarPorId(1L));
	}

	@Test
	void quandoBuscarPorId_entaoRetornaNulo() {
		when(this.repository.findById(anyLong())).thenReturn(this.tipoImovel);

		when(this.mapper.map(any(), any())).thenReturn(null);

		TipoImovelDto dto = this.service.buscarPorId(297L);

		assertThat(dto).isNull();
	}

	@Test
	void quandoBuscarPorNome_entaoRetornaObjeto() {
		when(this.repository.findByNome(anyString())).thenReturn(this.tipoImovel.get());

		when(this.mapper.map(this.tipoImovel.get(), TipoImovelDto.class)).thenReturn(this.tipoImovelDtoMock);

		assertThat(this.service.buscarPorNome("Apartamento")).isNotNull();
	}

	@Test
	void quandoVerificaSeExite() {
		when(this.repository.findById(anyLong())).thenReturn(this.tipoImovel);

		this.service.verificaSeExite(1L);
	}

	@Test
	void quandoVerificaSeExite_entaoRetornaException() {
		when(this.repository.findById(anyLong())).thenReturn(Optional.empty());

		assertThatExceptionOfType(RecursoNaoEncontradoException.class)
				.isThrownBy(() -> this.service.verificaSeExite(1L));
	}

	@Test
	void quandoAtualizar() {
		when(this.repository.findById(anyLong())).thenReturn(this.tipoImovel);

		when(this.mapper.map(any(), any())).thenReturn(any());

		this.service.atualizar(this.tipoImovelDtoMock);

		verify(this.service, atLeastOnce()).verificaSeExite(this.tipoImovelDtoMock.getId());
	}

	@Test
	public void quandoExcluir() {
		when(this.repository.findById(anyLong())).thenReturn(this.tipoImovel);

		when(this.mapper.map(this.tipoImovel.get(), TipoImovelDto.class)).thenReturn(this.tipoImovelDtoMock);

		when(this.mapper.map(this.tipoImovelDtoMock, TipoImovel.class)).thenReturn(this.tipoImovel.get());

		when(this.service.buscarPorId(anyLong())).thenReturn(this.tipoImovelDtoMock);

		this.service.excluir(anyLong());

		verify(this.repository, atLeastOnce()).delete(this.tipoImovel.get());

		verify(this.service, atLeastOnce()).verificaSeExite(anyLong());
	}

	@Test
	public void quandoBuscarTodosTipoImovel() {
		when(this.repository.findAll(Sort.by(Sort.Direction.ASC, "nome"))).thenReturn(Arrays.asList(this.tipoImovel.get()));

		assertThat(this.service.buscarTodosTipoImovel()).isNotNull();
	}

	@Test
	public void quandoBuscarTodosTiposImovel_entaoDeveRetornarListaVazia() {
		List<TipoImovel> imoveisVazio = Collections.EMPTY_LIST;

		when(this.repository.findAll(Sort.by(Sort.Direction.ASC, "nome"))).thenReturn(imoveisVazio);

		List<TipoImovelDto> imoveis = this.service.buscarTodosTipoImovel();

		assertThat(imoveis).hasSize(0);
	}

//	@Test
//	public void quandoBuscarTodosTipoImovel_entaoDeveRetornarListaNulo() {
//		List<TipoImovel> imoveisVazio = new ArrayList<>();
//
//		when(this.repository.findAll(Sort.by(Sort.Direction.ASC, "nome"))).thenReturn(null);
//
//		assertThat(this.service.buscarTodosTipoImovel()).isNull();
//	}

}
