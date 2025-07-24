package com.gabrielfigueiredo.biblioteca.api;

import com.gabrielfigueiredo.biblioteca.domain.Client;
import com.gabrielfigueiredo.biblioteca.dto.CreateClientDTO;
import com.gabrielfigueiredo.biblioteca.dto.UpdateClientDTO;
import com.gabrielfigueiredo.biblioteca.service.ClientService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clients")
@Tag(name = "Client Endpoints")
public class ClientApi {
    @Autowired
    ClientService clientService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid CreateClientDTO dto) {
        String id = clientService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAll() {
        List<Client> clientList = clientService.getAll();
        return ResponseEntity.ok().body(clientList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable @Parameter(description = "ULID Type") String id) {
        Client client = clientService.getById(id);
        return ResponseEntity.ok().body(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable @Parameter(description = "ULID Type") String id, @RequestBody @Valid UpdateClientDTO dto) {
        Client client = clientService.update(id, dto);
        return ResponseEntity.ok().body(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @Parameter(description = "ULID Type") String id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
