
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
import br.com.gral.anuncio.service.DadosComplementaresService;
import br.com.gral.anuncio.service.dto.DadosComplementaresDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@ApiModel("DadosComplementares")
@Api(value = "Endpoints para criar, consultar, atualizar e deletar um Recurso.", tags = { "DadosComplementares" })
@RestController
@RequestMapping(path = "/v1/dadoscomplementares", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class DadosComplementaresRest {

	private final DadosComplementaresService service;

	@PostMapping
	@ApiResponse(code = 201, message = "Recurso criado.")
	public ResponseEntity<DadosComplementaresDto> create(
			@Valid @RequestBody DadosComplementaresDto dadosComplementaresDto, BindingResult result) {

		if (result.hasErrors()) {
			throw new RequisicaoComErroException("Ops! ocorreu um erro ao tentar inserir esse registro, verifique");
		}

		DadosComplementaresDto dadosSalvos = service.salvar(dadosComplementaresDto);

		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(dadosSalvos.getId())
				.toUri();

		return ResponseEntity.created(uri).body(dadosSalvos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosComplementaresDto> buscarPorId(@PathVariable("id") Long id) {

		DadosComplementaresDto dadosComplementares = service.buscarPorId(id);

		return ResponseEntity.status(HttpStatus.OK).body(dadosComplementares);
	}

	@ApiOperation(value = "Endpoint responsável por atualizar um recurso pelo id, enviando no body da requisição o json do recurso com os dados atualizados.")
	@ApiResponse(code = 200, message = "Sucesso.")
	@PutMapping("/{id}")
	public void atualizar(@PathVariable Long id, @Valid @RequestBody DadosComplementaresDto dadosComplementaresDto,
			BindingResult result) {

		if (result.hasErrors()) {
			throw new RequisicaoComErroException("Ops! ocorreu um erro ao tentar inserir esse registro, verifique");
		}

		dadosComplementaresDto.setId(id);

		service.atualizar(dadosComplementaresDto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable("id") Long id) {
		service.excluir(id);
	}

}
