package com.fateczl.repository.orm;

import com.fateczl.entity.Cliente;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(value = "cliente")
public record ClienteOrmMongo(
        @Id
        String id,
        String nome,
        LocalDate dataNascimento,
        Cliente.Endereco endereco
) {}