package br.com.fiap.simuladospringpjunidades.dto.response;

import lombok.Builder;

@Builder
public record UnidadeResponse(


        Long id,

        String nome,

        String sigla,

        String descricao,

        UnidadeResponse macro

) {
}
