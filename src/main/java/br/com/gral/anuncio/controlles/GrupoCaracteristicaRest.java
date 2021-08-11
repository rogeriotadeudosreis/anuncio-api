
package br.com.gral.anuncio.controlles;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gral.anuncio.entity.GrupoCaracteristica;
import br.com.gral.anuncio.exception.RequisicaoComErroException;
import br.com.gral.anuncio.service.GrupoCaracteristicaService;
import br.com.gral.anuncio.service.dto.GrupoCaracteristicaDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@ApiModel("GrupoCaracteristica")
@Api(value = "Endpoints para criar, consultar, atualizar e deletar um Recurso.", tags = { "GrupoCaracteristica" })
@RestController
@RequestMapping(path = "/v1/grupocaracteristica", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GrupoCaracteristicaRest {

	private final GrupoCaracteristicaService service;
	
	@GetMapping
	public ResponseEntity<List<GrupoCaracteristicaDto>> getAllCaracteristicas(){
		
		List<GrupoCaracteristicaDto> listaDto = service.buscarTodosGrupoCaracteristica();
		
		return ResponseEntity.status(HttpStatus.OK).body(listaDto);
		
	}

	@PostMapping
	@ApiResponse(code = 201, message = "Recurso criado.")
	public ResponseEntity<GrupoCaracteristicaDto> create(
			@Valid @RequestBody GrupoCaracteristicaDto grupoCaracteristicaDto, BindingResult result) {

		if (result.hasErrors()) {
			throw new RequisicaoComErroException("Ops! ocorreu um erro ao tentar inserir esse registro, verifique");
		}

		GrupoCaracteristicaDto grupoCaracteristicaSalvo = service.salvar(grupoCaracteristicaDto);

		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
				.buildAndExpand(grupoCaracteristicaSalvo.getId()).toUri();

		return ResponseEntity.created(uri).body(grupoCaracteristicaSalvo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GrupoCaracteristicaDto> buscarPorId(@PathVariable("id") Long id) {

		GrupoCaracteristicaDto grupoCaracteristica = service.buscarPorId(id);

		return ResponseEntity.status(HttpStatus.OK).body(grupoCaracteristica);
	}

	@GetMapping("find/{name}")
	public ResponseEntity <List<GrupoCaracteristica>> buscarPorNome(@PathVariable String name) {
		
		List<GrupoCaracteristica> lista = this.service.findByNomeLike(name);
		
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}

	@ApiOperation(value = "Endpoint responsável por atualizar um recurso pelo id, enviando no body da requisição o json do recurso com os dados atualizados.")
	@ApiResponse(code = 200, message = "Sucesso.")
	@PutMapping("/{id}")
	public void atualizar(@PathVariable Long id, @Valid @RequestBody GrupoCaracteristicaDto grupoCaracteristicaDto,
			BindingResult result) {

		if (result.hasErrors()) {
			throw new RequisicaoComErroException("Ops! ocorreu um erro ao tentar inserir esse registro, verifique");
		}

		grupoCaracteristicaDto.setId(id);

		service.atualizar(grupoCaracteristicaDto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable("id") Long id) {
		service.excluir(id);
	}

}
