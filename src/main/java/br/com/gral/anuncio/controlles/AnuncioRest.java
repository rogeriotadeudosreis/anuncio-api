
package br.com.gral.anuncio.controlles;

import java.net.URI; 
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gral.anuncio.entity.Anuncio;
import br.com.gral.anuncio.exception.RequisicaoComErroException;
import br.com.gral.anuncio.service.AnuncioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@Controller
@CrossOrigin("*")
@ApiModel("Anuncio")
@Api(value = "Endpoints para criar, consultar, atualizar e deletar um Recurso.", tags = { "Anuncio" })
@RequestMapping(path = "/v1/anuncio", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnuncioRest {

	@Autowired
	private AnuncioService service;

	@GetMapping
	public ResponseEntity<List<Anuncio>> buscarAnuncios() {
		List<Anuncio> listarAnuncio = service.buscarTodosAnuncios();
		return ResponseEntity.ok().body(listarAnuncio);
	}

	@PostMapping
	@ApiResponse(code = 201, message = "Recurso criado.")
	public ResponseEntity<Anuncio> criar(@Valid @RequestBody Anuncio anuncio, BindingResult result) {

		if (result.hasErrors()) {
			throw new RequisicaoComErroException("Ops! ocorreu um erro ao tentar inserir esse registro, verifique");
		}
		
		Anuncio anuncioSalvo = service.salvar(anuncio);

		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
				.buildAndExpand(anuncioSalvo.getId()).toUri();

		return ResponseEntity.created(uri).body(anuncioSalvo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Anuncio> buscarPorId(@PathVariable("id") Long id) {

		Anuncio anuncio = service.buscarPorId(id);

		return ResponseEntity.status(HttpStatus.OK).body(anuncio);
	}

	@ApiOperation(value = "Endpoint responsável por atualizar um recurso pelo id, enviando no body da requisição o json do recurso com os dados atualizados.")
	@ApiResponse(code = 200, message = "Sucesso.")
	@PutMapping("/{id}")
	public void atualizar(@PathVariable Long id, @Valid @RequestBody Anuncio anuncio, BindingResult result) {

		if (result.hasErrors()) {
			throw new RequisicaoComErroException("Ops! ocorreu um erro ao tentar inserir esse registro, verifique");
		}

//		anuncio.setId(id);

		service.atualizar(anuncio);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable("id") Long id) {
		service.excluir(id);
	}

}
