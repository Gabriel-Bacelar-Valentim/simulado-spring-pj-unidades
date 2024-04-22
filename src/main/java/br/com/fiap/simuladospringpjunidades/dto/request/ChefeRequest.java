package br.com.fiap.simuladospringpjunidades.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ChefeRequest(


        @NotNull(message = "Informe se é [true] um chefe substituto ou não [false]")
        Boolean substituto,

        @Valid
        @NotNull(message = "O atributo usuário é obrigatório")
        AbstractRequest usuario,

        @Valid
        @NotNull(message = "O atributo unidade é obrigatório")
        AbstractRequest unidade,

        @NotNull(message = "A data de inicio da chefia deve ser informada")
        LocalDateTime inicio,

        LocalDateTime fim

) {
}
