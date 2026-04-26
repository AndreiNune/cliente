package com.fateczl.controller.adapter;

import com.fateczl.controller.dto.request.ClienteRequest;
import com.fateczl.controller.dto.response.ClienteResponse;
import com.fateczl.entity.Cliente;

import java.util.UUID;

public class ClienteControllerAdapter {
    private ClienteControllerAdapter() {}

    public static Cliente castRequest(ClienteRequest request) {
        var end = request.endereco();
        return new Cliente(
                UUID.randomUUID().toString(),
                request.nome(),
                request.dataNascimento(),
                new Cliente.Endereco(end.logradouro(), end.numero(), end.cidade(), end.estado())
        );
    }

    public static Cliente castRequestComId(String id, ClienteRequest request) {
        var end = request.endereco();
        return new Cliente(
                id,
                request.nome(),
                request.dataNascimento(),
                new Cliente.Endereco(end.logradouro(), end.numero(), end.cidade(), end.estado())
        );
    }

    public static ClienteResponse castResponse(Cliente cliente) {
        var end = cliente.endereco();
        return new ClienteResponse(
                cliente.id(),
                cliente.nome(),
                cliente.dataNascimento(),
                new ClienteResponse.EnderecoResponse(
                        end.logradouro(), end.numero(), end.cidade(), end.estado()
                )
        );
    }
}
