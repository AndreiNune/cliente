package com.fateczl.controller.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ClienteRequest(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotNull(message = "A data de nascimento é obrigatória")
        LocalDate dataNascimento,

        @NotNull(message = "O objeto endereco é obrigatório")
        @Valid // Repassa a validação para os campos de dentro do EnderecoRequest
        EnderecoRequest endereco
) {
    public record EnderecoRequest(
            @NotBlank(message = "O logradouro é obrigatório")
            String logradouro,

            @NotBlank(message = "O número é obrigatório")
            String numero,

            @NotBlank(message = "A cidade é obrigatória")
            String cidade,

            @NotBlank(message = "O estado é obrigatório")
            String estado
    ) {}
}