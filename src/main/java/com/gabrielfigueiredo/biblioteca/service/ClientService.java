package com.gabrielfigueiredo.biblioteca.service;

import com.gabrielfigueiredo.biblioteca.domain.Client;
import com.gabrielfigueiredo.biblioteca.dto.CreateClientDTO;
import com.gabrielfigueiredo.biblioteca.dto.UpdateClientDTO;
import com.gabrielfigueiredo.biblioteca.infra.exceptions.IdNotFoundException;
import com.gabrielfigueiredo.biblioteca.infra.exceptions.InvalidEmailException;
import com.gabrielfigueiredo.biblioteca.repository.ClientRepository;
import com.gabrielfigueiredo.biblioteca.utils.ClientUtils;

import de.huxhorn.sulky.ulid.ULID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public String create(CreateClientDTO dto) {
        String cleanPhone = ClientUtils.cleanPhone(dto.phone());
        if (clientRepository.getRepository().emailExists(dto.email())) {
            throw new InvalidEmailException("Email já existe");
        }

        String id = new ULID().nextULID();
        Client newClient = new Client(id, dto, cleanPhone);

        clientRepository.getRepository().create(newClient);

        return id;
    }

    public Client getById(String id) {
        return clientRepository.getRepository().getById(id);
    }

    public List<Client> getAll() {
        return clientRepository.getRepository().getAll();
    }

    public void deleteById(String id) {
        boolean isSuccess = clientRepository.getRepository().deleteById(id);
        if (!isSuccess) {
            throw new IdNotFoundException("Id Não encontrado");
        }
    }

    public Client update(String id, UpdateClientDTO dto) {
        Client client = this.getById(id);

        if (dto.name() != null) {
            client.setName(dto.name());
        }
        if (dto.phone() != null) {
            String cleanPhone = ClientUtils.cleanPhone(dto.phone());
            client.setPhone(cleanPhone);
        }
        if (dto.email() != null) {
            if (clientRepository.getRepository().emailExists(dto.email()) && !Objects.equals(client.getEmail(), dto.email())) {
                throw new InvalidEmailException("Email já existe");
            }
            client.setEmail(dto.email());
        }

        Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());
        client.setUpdatedAt(updatedAt);

        if (clientRepository.getRepository().update(client)) {
            return client;
        } else {
            throw new RuntimeException("Erro inesperado");
        }
    }
}
