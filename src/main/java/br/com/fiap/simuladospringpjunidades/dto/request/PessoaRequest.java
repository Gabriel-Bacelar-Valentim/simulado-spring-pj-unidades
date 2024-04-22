package br.com.fiap.simuladospringpjunidades.dto.request;

import br.com.fiap.simuladospringpjunidades.entity.Tipo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PessoaRequest(

        @Size(min = 3, max = 50)
        @NotNull(message = "Nome é obrigatório")
        String nome,

        @Size(min = 3, max = 255)
        @NotNull(message = "Sobrenome é obrigatório")
        String sobrenome,

        @Email(message = "Email é inválido")
        @NotNull(message = "e-mail é obrigatório")
        String email,

        @PastOrPresent(message = "Não aceitamos data de nascimento no futuro")
        @NotNull(message = "Data de nascimento é obrigatório")
        LocalDate nascimento,

        @NotNull(message = "O tipo da pessoa é obrigatório")
        Tipo tipo

) {
}
