
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

import br.com.gral.anuncio.entity.Imagem;
import br.com.gral.anuncio.exception.RequisicaoComErroException;
import br.com.gral.anuncio.service.ImagemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@ApiModel("Imagem")
@Api(value = "Endpoints para criar, consultar, atualizar e deletar um Recurso.", tags = { "Imagem" })
@RestController
@RequestMapping(path = "/v1/imagem", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ImagemRest {

	private final ImagemService service;

	@PostMapping
	@ApiResponse(code = 201, message = "Recurso criado.")
	public ResponseEntity<Imagem> create(@Valid @RequestBody Imagem imagem, BindingResult result) {

		if (result.hasErrors()) {
			throw new RequisicaoComErroException("Ops! ocorreu um erro ao tentar inserir esta imagem");
		}
		
		Imagem imovelSalvo = service.salvar(imagem);

		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(imovelSalvo.getId())
				.toUri();

		return ResponseEntity.created(uri).body(imovelSalvo);
	}
	
	@GetMapping
	public ResponseEntity<List<Imagem>> ListarImagens() {

		List<Imagem> lista = service.listarImagens();

		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Imagem> buscarPorId(@PathVariable("id") Long id) {

		Imagem imagem = service.buscarPorId(id);

		return ResponseEntity.status(HttpStatus.OK).body(imagem);
	}

	@ApiOperation(value = "Endpoint responsável por atualizar um recurso pelo id, enviando no body da requisição o json do recurso com os dados atualizados.")
	@ApiResponse(code = 200, message = "Sucesso.")
	@PutMapping("/{id}")
	public void atualizar(@PathVariable Long id, @Valid @RequestBody Imagem imagem) {

		imagem.setId(id);

		service.atualizar(imagem);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable("id") Long id) {
		service.excluir(id);
	}

}
