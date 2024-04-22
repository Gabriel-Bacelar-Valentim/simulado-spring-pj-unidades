package br.com.fiap.simuladospringpjunidades.resource;

import br.com.fiap.simuladospringpjunidades.dto.request.UsuarioRequest;
import br.com.fiap.simuladospringpjunidades.dto.response.UsuarioResponse;
import br.com.fiap.simuladospringpjunidades.entity.Pessoa;
import br.com.fiap.simuladospringpjunidades.entity.Tipo;
import br.com.fiap.simuladospringpjunidades.entity.Usuario;
import br.com.fiap.simuladospringpjunidades.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource implements ResourceDTO<UsuarioRequest, UsuarioResponse> {

    @Autowired
    UsuarioService service;


    @GetMapping
    public ResponseEntity<Collection<UsuarioResponse>> findAll(

            @RequestParam(name = "pessoa.tipo", required = false) Tipo pessoaTipo,
            @RequestParam(name = "pessoa.name", required = false) String pessoaNome,
            @RequestParam(name = "pessoa.sobrenome", required = false) String pessoaSobrenome,
            @RequestParam(name = "pessoa.nascimento", required = false) LocalDate pessoaNascimento,
            @RequestParam(name = "pessoa.email", required = false) String pessoaEmail,
            @RequestParam(name = "username", required = false) String username

    ) {

        var pessoa = Pessoa.builder()
                .nome(pessoaNome)
                .sobrenome(pessoaSobrenome)
                .nascimento(pessoaNascimento)
                .email(pessoaEmail)
                .tipo(pessoaTipo)
                .build();

        var usuario = Usuario.builder()
                .username(username)
                .pessoa(pessoa)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Usuario> example = Example.of(usuario, matcher);
        List<Usuario> usuarios = service.findAll(example);

        var response = usuarios.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<UsuarioResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }


    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<UsuarioResponse> save(@RequestBody @Valid UsuarioRequest r) {
        var entity = service.toEntity(r);
        var saved = service.save(entity);
        var response = service.toResponse(saved);


        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);

    }
}
