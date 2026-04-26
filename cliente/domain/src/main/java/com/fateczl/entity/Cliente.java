package com.fateczl.entity;

import java.time.LocalDate;

public record Cliente(
        String id,
        String nome,
        LocalDate dataNascimento,
        Endereco endereco
) {
    public record Endereco(
            String logradouro,
            String numero,
            String cidade,
            String estado
    ) {}
}
