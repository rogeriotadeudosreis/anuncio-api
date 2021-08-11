
package br.com.gral.anuncio.controlles;

import java.net.URI;

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
import br.com.gral.anuncio.service.EnderecoService;
import br.com.gral.anuncio.service.dto.EnderecoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@ApiModel("Endereco")
@Api(value = "Endpoints para criar, consultar, atualizar e deletar um Recurso.", tags = { "Endereco" })
@RestController
@RequestMapping(path = "/v1/endereco", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EnderecoRest {

	private final EnderecoService service;

	@PostMapping
	@ApiResponse(code = 201, message = "Recurso criado.")
	public ResponseEntity<EnderecoDto> create(@Valid @RequestBody EnderecoDto enderecoDto, BindingResult result) {

		if (result.hasErrors()) {
			throw new RequisicaoComErroException("Ops! ocorreu um erro ao tentar inserir esse registro, verifique");
		}

		EnderecoDto enderecoSalvo = service.salvar(enderecoDto);

		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
				.buildAndExpand(enderecoSalvo.getId()).toUri();

		return ResponseEntity.created(uri).body(enderecoSalvo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EnderecoDto> buscarPorId(@PathVariable("id") Long id) {

		EnderecoDto endereco = service.buscarPorId(id);

		return ResponseEntity.status(HttpStatus.OK).body(endereco);
	}

	@ApiOperation(value = "Endpoint responsável por atualizar um recurso pelo id, enviando no body da requisição o json do recurso com os dados atualizados.")
	@ApiResponse(code = 200, message = "Sucesso.")
	@PutMapping("/{id}")
	public void atualizar(@PathVariable Long id, @Valid @RequestBody EnderecoDto enderecoDto, BindingResult result) {

		if (result.hasErrors()) {
			throw new RequisicaoComErroException("Ops! ocorreu um erro ao tentar inserir esse registro, verifique");
		}

		enderecoDto.setId(id);

		service.atualizar(enderecoDto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable("id") Long id) {
		service.excluir(id);
	}

}
