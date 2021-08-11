
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

import br.com.gral.anuncio.exception.RequisicaoComErroException;
import br.com.gral.anuncio.service.TipoEmpreendimentoService;
import br.com.gral.anuncio.service.dto.TipoEmpreendimentoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@ApiModel("TipoEmpreendimento")
@Api(value = "Endpoints para criar, consultar, atualizar e deletar um Recurso.", tags = {"TipoEmpreendimento"})
@RestController 
@RequestMapping(path = "/v1/tipoempreendimento", produces = MediaType.APPLICATION_JSON_VALUE) 
@RequiredArgsConstructor
public class TipoEmpreendimentoRest {

	private final TipoEmpreendimentoService service;
	
	@GetMapping
	public ResponseEntity<List<TipoEmpreendimentoDto>> getAllTipoEmpreendimentos(){
		
		List<TipoEmpreendimentoDto> listaDto = service.buscarTodosTipoEmpreendimento();
		
		return ResponseEntity.status(HttpStatus.OK).body(listaDto);
		
	}
	
	@PostMapping 
	@ApiResponse(code = 201, message = "Recurso criado.")
	public ResponseEntity<TipoEmpreendimentoDto> create(@Valid @RequestBody TipoEmpreendimentoDto tipoEmpreendimentoDto, 
			BindingResult result) {
		
		if(result.hasErrors()) {
			throw new RequisicaoComErroException("Ops! ocorreu um erro ao tentar inserir esse registro, verifique");
		}
		
		TipoEmpreendimentoDto empreendimentoSalvo = service.salvar(tipoEmpreendimentoDto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/{id}")
				.buildAndExpand(empreendimentoSalvo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(empreendimentoSalvo);
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<TipoEmpreendimentoDto> buscarPorId(@PathVariable("id") Long id) {		
		
		TipoEmpreendimentoDto tipoEmpreendimento = service.buscarPorId(id);	
		
		return ResponseEntity.status(HttpStatus.OK).body(tipoEmpreendimento);
	}
	
//	@GetMapping("/{name}")
//	public ResponseEntity<TipoEmpreendimentoDto> buscarPorNome(@RequestParam String name) {
//		
//		TipoEmpreendimentoDto dtoBanco = service.buscarPorNome(name);
//		
//		return ResponseEntity.status(HttpStatus.OK).body(dtoBanco);
//	}	
	
	
	@ApiOperation(value="Endpoint responsável por atualizar um recurso pelo id, enviando no body da requisição o json do recurso com os dados atualizados.")	
    @ApiResponse(code = 200, message = "Sucesso.")
	@PutMapping("/{id}")
	public void atualizar(@PathVariable Long id, @Valid @RequestBody TipoEmpreendimentoDto tipoEmpreendimentoDto) {
		
		tipoEmpreendimentoDto.setId(id);
		
		service.atualizar(tipoEmpreendimentoDto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable("id") Long id) {
		service.excluir(id);
	}


}
