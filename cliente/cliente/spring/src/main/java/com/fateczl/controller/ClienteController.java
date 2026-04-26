package com.fateczl.controller;

import com.fateczl.controller.adapter.ClienteControllerAdapter;
import com.fateczl.controller.dto.request.ClienteRequest;
import com.fateczl.controller.dto.response.ClienteResponse;
import com.fateczl.entity.Cliente;
import com.fateczl.repository.ClienteRepository;
import jakarta.validation.Valid; // <-- Importação necessária para a validação
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> salvar(@RequestBody @Valid ClienteRequest request) {
        // O @Valid garante que o request tem o endereço preenchido, evitando o NullPointerException
        Cliente cliente = ClienteControllerAdapter.castRequest(request);
        Cliente salvo = clienteRepository.salve(cliente);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ClienteControllerAdapter.castResponse(salvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable String id) {
        return clienteRepository.buscaPorId(id)
                .map(ClienteControllerAdapter::castResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> buscarTodos() {
        List<ClienteResponse> clientes = clienteRepository.buscaTodos()
                .stream()
                .map(ClienteControllerAdapter::castResponse)
                .toList();

        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizar(
            @PathVariable String id,
            @RequestBody @Valid ClienteRequest request // <-- @Valid adicionado aqui também!
    ) {
        return clienteRepository.buscaPorId(id)
                .map(existente -> {
                    // Como é uma atualização, também precisamos proteger contra envios nulos
                    Cliente atualizado = ClienteControllerAdapter.castRequestComId(id, request);
                    return ResponseEntity.ok(ClienteControllerAdapter.castResponse(
                            clienteRepository.atualize(atualizado)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable String id) {
        return clienteRepository.buscaPorId(id)
                .map(existente -> {
                    clienteRepository.delete(id);
                    return ResponseEntity.<Void>noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}