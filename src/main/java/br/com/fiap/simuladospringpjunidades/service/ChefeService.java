package br.com.fiap.simuladospringpjunidades.service;

import br.com.fiap.simuladospringpjunidades.dto.request.ChefeRequest;
import br.com.fiap.simuladospringpjunidades.dto.response.ChefeResponse;
import br.com.fiap.simuladospringpjunidades.entity.Chefe;
import br.com.fiap.simuladospringpjunidades.repository.ChefeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ChefeService implements ServiceDTO<Chefe, ChefeRequest, ChefeResponse> {

    @Autowired
    private ChefeRepository repo;

    @Autowired
    private UnidadeService unidadeService;

    @Autowired
    private UsuarioService usuarioService;


    @Override
    public Chefe toEntity(ChefeRequest r) {
        var unidade = unidadeService.findById( r.unidade().id() );
        var usuario = usuarioService.findById( r.usuario().id() );
        return Chefe.builder()
                .unidade( unidade )
                .inicio( Objects.isNull( r.inicio() ) ? null : r.inicio() )
                .fim( Objects.isNull( r.fim() ) ? null : r.fim() )
                .substituto( r.substituto() )
                .usuario( usuario )
                .build();
    }

    @Override
    public ChefeResponse toResponse(Chefe e) {
        return ChefeResponse.builder()
                .id( e.getId() )
                .unidade( unidadeService.toResponse( e.getUnidade() ) )
                .inicio( e.getInicio() )
                .fim( e.getFim() )
                .substituto( e.getSubstituto() )
                .usuario( usuarioService.toResponse( e.getUsuario() ) )
                .build();
    }

    @Override
    public List<Chefe> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Chefe> findAll(Example<Chefe> example) {
        return repo.findAll( example );
    }

    @Override
    public Chefe findById(Long id) {
        return repo.findById( id ).orElse( null );
    }

    @Override
    public Chefe save(Chefe e) {
        return repo.save( e );
    }
}
