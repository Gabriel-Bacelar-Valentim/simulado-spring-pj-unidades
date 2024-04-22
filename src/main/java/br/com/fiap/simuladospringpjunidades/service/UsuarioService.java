package br.com.fiap.simuladospringpjunidades.service;

import br.com.fiap.simuladospringpjunidades.dto.request.UsuarioRequest;
import br.com.fiap.simuladospringpjunidades.dto.response.UsuarioResponse;
import br.com.fiap.simuladospringpjunidades.entity.Usuario;
import br.com.fiap.simuladospringpjunidades.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService implements ServiceDTO<Usuario, UsuarioRequest, UsuarioResponse> {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private PessoaService pessoaService;

    @Override
    public Usuario toEntity(UsuarioRequest r) {

        var pessoa = pessoaService.toEntity(r.pessoa());

        return Usuario.builder()
                .password(r.password())
                .pessoa(pessoa)
                .username(r.username())
                .build();
    }

    @Override
    public UsuarioResponse toResponse(Usuario e) {

        if (Objects.isNull(e)) return null;

        var pessoa = pessoaService.toResponse(e.getPessoa());

        return UsuarioResponse.builder()
                .id(e.getId())
                .pessoa(pessoa)
                .username(e.getUsername())
                .build();
    }

    @Override
    public List<Usuario> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Usuario> findAll(Example<Usuario> example) {
        return repo.findAll(example);
    }

    @Override
    public Usuario findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Usuario save(Usuario e) {
        return repo.save(e);
    }
}
