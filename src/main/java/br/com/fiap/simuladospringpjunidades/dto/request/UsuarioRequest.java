package br.com.fiap.simuladospringpjunidades.dto.request;

import br.com.fiap.simuladospringpjunidades.entity.Pessoa;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UsuarioRequest(

        @Email(message = "Username inválido! Deve ser o email da empresa")
        @NotNull(message = "Username não pode ser nulo")
        String username,

        /**
         * Explicando em mais detalhes:
         *
         * /^
         *   (?=.*\d)              // deve conter ao menos um dígito
         *   (?=.*[a-z])           // deve conter ao menos uma letra minúscula
         *   (?=.*[A-Z])           // deve conter ao menos uma letra maiúscula
         *   (?=.*[$*&@#])         // deve conter ao menos um caractere especial
         *   [0-9a-zA-Z$*&@#]{8,16}  // deve conter ao menos 8 e no máximo 16 dos caracteres mencionados
         * $/
         */
//        @Pattern(
//                //regexp = "/^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,}$/",
//                message = "Senha muito fraca!!")
        @NotNull(message = "senha não pode ser nulo")
        String password,


        @Valid
        @NotNull(message = "O atributo pessoa é obrigatório")
        PessoaRequest pessoa


) {
}
