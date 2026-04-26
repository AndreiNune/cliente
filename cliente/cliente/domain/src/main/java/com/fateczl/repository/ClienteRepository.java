package com.fateczl.repository;

import com.fateczl.entity.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    Cliente salve(Cliente cliente);
    Optional<Cliente> buscaPorId(String id);
    List<Cliente> buscaTodos();
    Cliente atualize(Cliente cliente);
    void delete(String id);
}