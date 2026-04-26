package com.fateczl.controller.dto.response;

import java.time.LocalDate;

public record ClienteResponse(
        String id,
        String nome,
        LocalDate dataNascimento,
        EnderecoResponse endereco
) {
    public record EnderecoResponse(
            String logradouro,
            String numero,
            String cidade,
            String estado
    ) {}
}
