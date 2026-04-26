package com.fateczl.repository;

import com.fateczl.entity.Cliente;
import com.fateczl.repository.adapter.ClienteRepositoryAdapter;
import com.fateczl.repository.mongo.ClienteRepositoryWithMongoDB;
import com.fateczl.repository.orm.ClienteOrmMongo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private final ClienteRepositoryWithMongoDB repository;

    public ClienteRepositoryImpl(ClienteRepositoryWithMongoDB repository) {
        this.repository = repository;
    }

    @Override
    public Cliente salve(Cliente cliente) {
        ClienteOrmMongo orm = ClienteRepositoryAdapter.castEntity(cliente);
        return ClienteRepositoryAdapter.castOrm(repository.save(orm));
    }

    @Override
    public Optional<Cliente> buscaPorId(String id) {
        return repository.findById(id)
                .map(ClienteRepositoryAdapter::castOrm);
    }

    @Override
    public List<Cliente> buscaTodos() {
        return repository.findAll()
                .stream()
                .map(ClienteRepositoryAdapter::castOrm)
                .toList();
    }

    @Override
    public Cliente atualize(Cliente cliente) {
        ClienteOrmMongo orm = ClienteRepositoryAdapter.castEntity(cliente);
        return ClienteRepositoryAdapter.castOrm(repository.save(orm));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
