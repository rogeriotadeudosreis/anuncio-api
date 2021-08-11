package br.com.gral.anuncio.controlles;

import br.com.gral.anuncio.service.UsuarioService;
import br.com.gral.anuncio.service.dto.UsuarioDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@ApiModel("Usuario")
@Api(value = "Endpoints para criar, consultar, atualizar e deletar um Recurso.", tags = {"Usuario"})
@RestController
@RequestMapping(path = "/v1/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UsuarioResource {

    private final UsuarioService service;

    @PostMapping("/login")
    public ResponseEntity<UsuarioDto> autenticar(@Valid @RequestBody UsuarioDto dto) {
        UsuarioDto usuarioDto = service.login(dto);
        return ResponseEntity.ok().body(usuarioDto);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> salvar(@Valid @RequestBody UsuarioDto dto) {
        UsuarioDto usuarioDto = service.salvar(dto);
        return ResponseEntity.ok().body(usuarioDto);
    }

}
