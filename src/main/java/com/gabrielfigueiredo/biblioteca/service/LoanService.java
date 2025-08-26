package com.gabrielfigueiredo.biblioteca.service;

import com.gabrielfigueiredo.biblioteca.domain.Loan;
import com.gabrielfigueiredo.biblioteca.dto.inventoryDTOs.InventoryResponseDTO;
import com.gabrielfigueiredo.biblioteca.dto.loanDTOs.CreateLoanDTO;
import com.gabrielfigueiredo.biblioteca.dto.loanDTOs.LoanResponseDTO;
import com.gabrielfigueiredo.biblioteca.dto.loanDTOs.UpdateLoanDTO;
import com.gabrielfigueiredo.biblioteca.infra.exceptions.DatabaseException;
import com.gabrielfigueiredo.biblioteca.infra.exceptions.IdNotFoundException;
import com.gabrielfigueiredo.biblioteca.infra.exceptions.InvalidDateException;
import com.gabrielfigueiredo.biblioteca.infra.exceptions.InventoryNotAvailableException;
import com.gabrielfigueiredo.biblioteca.repository.LoanRepository;
import de.huxhorn.sulky.ulid.ULID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final ClientService clientService;
    private final InventoryService inventoryService;

    @Autowired
    public LoanService(LoanRepository loanRepository, ClientService clientService, InventoryService inventoryService) {
        this.loanRepository = loanRepository;
        this.clientService = clientService;
        this.inventoryService = inventoryService;
    }

    public String create(CreateLoanDTO dto) {
        validate(dto);

        String id = new ULID().nextULID();

        if (inventoryService.updateAvailable(dto.inventoryId())) {
            Loan loan = new Loan(id, dto);

            loanRepository.getRepository().create(loan);
        } else {
            throw new DatabaseException("Erro interno");
        }

        return id;
    }

    public List<LoanResponseDTO> getAll() {
        return loanRepository.getRepository().getAll();
    }

    public List<LoanResponseDTO> getAllOverdue() {
        return List.of();
        //return loanRepository.getRepository().getAllOverdue();
    }

    public LoanResponseDTO getById(String id) {
        Optional<Loan> loan = loanRepository.getRepository().getById(id);
        if (loan.isEmpty()) {
            throw new IdNotFoundException("Empréstimo não encotrado");
        }
        return null;

        //return loan.get();
    }

    public LoanResponseDTO update(String id, UpdateLoanDTO dto) {
        Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());
        return null;
        //return new Loan();
    }

    public LoanResponseDTO returnLoan(String id) {
        Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());

        loanRepository.getRepository().returnLoan(id);
        return null;

    }

    private void validate(CreateLoanDTO dto) {
        Timestamp timestamp = new Timestamp(new Date().getTime());

        if (dto.rentDate().before(timestamp) || dto.dueDate().before(timestamp) || dto.dueDate().before(dto.rentDate())) {
            throw new InvalidDateException("Data inválida");
        }

        if (clientService.getById(dto.clientId()) == null) {
            throw new IdNotFoundException("Id do cliente não encontrado");
        }

        InventoryResponseDTO inventory = inventoryService.getById(dto.inventoryId());

        if (inventory == null) {
            throw new IdNotFoundException("Id do inventário não encontrado.");
        }

        if (!inventory.isAvailable()) {
            throw new InventoryNotAvailableException("Inventário não está disponível.");
        }
    }
}
