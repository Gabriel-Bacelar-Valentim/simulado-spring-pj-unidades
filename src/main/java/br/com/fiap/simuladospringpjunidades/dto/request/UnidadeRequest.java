package br.com.fiap.simuladospringpjunidades.dto.request;

import br.com.fiap.simuladospringpjunidades.entity.Unidade;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UnidadeRequest(

        @Size(min = 2, max = 255)
        @NotNull(message = "Nome é obrigatório")
        String nome,
        @Size(min = 2, max = 100)
        @NotNull(message = "Sigla é obrigatório")
        String sigla,
        @Size(min = 5, max = 2000)
        @NotNull(message = "Descrição é obrigatório")
        String descricao,

        @Valid
        AbstractRequest macro


) {
}
