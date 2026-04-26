package com.fateczl.repository.adapter;

import com.fateczl.entity.Cliente;
import com.fateczl.repository.orm.ClienteOrmMongo;

public class ClienteRepositoryAdapter {
    private ClienteRepositoryAdapter() {}

    public static Cliente castOrm(ClienteOrmMongo orm) {
        return new Cliente(
                orm.id(),
                orm.nome(),
                orm.dataNascimento(),
                orm.endereco()
        );
    }

    public static ClienteOrmMongo castEntity(Cliente entity) {
        return new ClienteOrmMongo(
                entity.id(),
                entity.nome(),
                entity.dataNascimento(),
                entity.endereco()
        );
    }
}
