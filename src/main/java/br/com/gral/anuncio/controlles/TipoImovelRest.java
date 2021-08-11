
package br.com.gral.anuncio.controlles;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gral.anuncio.service.TipoImovelService;
import br.com.gral.anuncio.service.dto.TipoImovelDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@ApiModel("TipoImovel")
@Api(value = "Endpoints para criar, consultar, atualizar e deletar um Recurso.", tags = {"TipoImovel"})
@RestController 
@RequestMapping(path = "/v1/tipoimovel", produces = MediaType.APPLICATION_JSON_VALUE) 
@RequiredArgsConstructor
public class TipoImovelRest {

	private final TipoImovelService service;
	
	@GetMapping
	public ResponseEntity<List<TipoImovelDto>> getAllTipoImovels(){
		
		List<TipoImovelDto> listaDto = service.buscarTodosTipoImovel();
		
		return ResponseEntity.status(HttpStatus.OK).body(listaDto);
		
	}
	
	@PostMapping 
	@ApiResponse(code = 201, message = "Recurso criado.")
	public ResponseEntity<TipoImovelDto> create(@Valid @RequestBody TipoImovelDto tipoImovelDto) {
		
		TipoImovelDto imovelSalvo = service.salvar(tipoImovelDto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/{id}")
				.buildAndExpand(imovelSalvo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(imovelSalvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TipoImovelDto> buscarPorId(@PathVariable("id") Long id) {		
		
		TipoImovelDto tipoImovel = service.buscarPorId(id);	
		
		return ResponseEntity.status(HttpStatus.OK).body(tipoImovel);
	}

	@GetMapping("/filtro")
	public ResponseEntity<TipoImovelDto> buscar(@RequestParam("id") @Valid Long id) {

		TipoImovelDto tipoImovel = service.buscarPorId(id);

		return ResponseEntity.status(HttpStatus.OK).body(tipoImovel);
	}
	
//	@GetMapping("/{name}")
//	public ResponseEntity<TipoImovelDto> buscarPorNome(@RequestParam String name) {
//		
//		TipoImovelDto dtoBanco = service.buscarPorNome(name);
//		
//		return ResponseEntity.status(HttpStatus.OK).body(dtoBanco);
//	}	
	
	
	@ApiOperation(value="Endpoint responsável por atualizar um recurso pelo id, enviando no body da requisição o json do recurso com os dados atualizados.")	
    @ApiResponse(code = 200, message = "Sucesso.")
	@PutMapping("/{id}")
	public void atualizar(@PathVariable Long id, @Valid @RequestBody TipoImovelDto tipoImovelDto) {
		
		tipoImovelDto.setId(id);
		
		service.atualizar(tipoImovelDto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable("id") Long id) {
		service.excluir(id);
	}


}
