
package br.com.gral.anuncio.controlles;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.gral.anuncio.entity.Caracteristica;
import br.com.gral.anuncio.exception.RecursoNaoEncontradoException;
import br.com.gral.anuncio.exception.RequisicaoComErroException;
import br.com.gral.anuncio.service.CaracteristicaService;
import br.com.gral.anuncio.service.dto.CaracteristicaDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@ApiModel("Caracteristica")
@Api(value = "Endpoints para criar, consultar, atualizar e deletar um Recurso.", tags = { "Caracteristica" })
@RestController
@RequestMapping(path = "/v1/caracteristica", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CaracteristicaRest {

	@Autowired
	private final CaracteristicaService service;
	
	@GetMapping
	public ResponseEntity<List<CaracteristicaDto>> getAllCaracteristicas(){
		
		List<CaracteristicaDto> listaDto = service.buscarCaracteristicas();
		
		return ResponseEntity.status(HttpStatus.OK).body(listaDto);
		
	}

	@PostMapping
	@ApiResponse(code = 201, message = "Recurso criado.")
	public ResponseEntity<CaracteristicaDto> create(@Valid @RequestBody CaracteristicaDto caracteristicaDto,
			BindingResult result) {

		if (result.hasErrors()) {
			throw new RequisicaoComErroException("Ops! ocorreu um erro ao tentar inserir esse registro, verifique");
		}

		CaracteristicaDto caracteristicaSalva = service.salvar(caracteristicaDto);

		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
				.buildAndExpand(caracteristicaSalva.getId()).toUri();

		return ResponseEntity.created(uri).body(caracteristicaSalva);
	}
	
	@ApiOperation(value = "Endpoint responsável por atualizar um recurso pelo id, enviando no body da requisição o json do recurso com os dados atualizados.")
	@ApiResponse(code = 200, message = "Sucesso.")
	@PutMapping("/{id}")
	public void atualizar(@PathVariable Long id, @Valid @RequestBody CaracteristicaDto caracteristicaDto,
			BindingResult result) {

		if (result.hasErrors()) {
			throw new RequisicaoComErroException("Ops! ocorreu um erro ao tentar inserir esse registro, verifique");
		}

		caracteristicaDto.setId(id);

		service.atualizar(caracteristicaDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CaracteristicaDto> buscarPorId(@PathVariable("id") Long id) {

		CaracteristicaDto caracteristica = service.buscarPorId(id);

		return ResponseEntity.status(HttpStatus.OK).body(caracteristica);
	}

//	@GetMapping("/{nome}")
//	public ResponseEntity<CaracteristicaDto> buscarPorNome(@RequestParam String name) {
//
//		CaracteristicaDto dtoBanco = service.buscarPorNome(name);
//
//		return ResponseEntity.status(HttpStatus.OK).body(dtoBanco);
//	}

	
	
	@GetMapping("find/{name}")
	public ResponseEntity <List<Caracteristica>> buscarPorNome(@PathVariable String name) {
		
		List<Caracteristica> lista = this.service.BuscarPorNomeLike(name);
		
		if (lista.isEmpty()) {
			throw new RecursoNaoEncontradoException("O nome procurado não foi encontrado.");
		}		
		
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable("id") Long id) {
		service.excluir(id);
	}

}
