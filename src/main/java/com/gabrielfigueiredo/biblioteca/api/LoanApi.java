package com.gabrielfigueiredo.biblioteca.api;

import com.gabrielfigueiredo.biblioteca.domain.Loan;
import com.gabrielfigueiredo.biblioteca.dto.loanDTOs.CreateLoanDTO;
import com.gabrielfigueiredo.biblioteca.dto.loanDTOs.LoanResponseDTO;
import com.gabrielfigueiredo.biblioteca.dto.loanDTOs.UpdateLoanDTO;
import com.gabrielfigueiredo.biblioteca.service.LoanService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/loans")
@Tag(name = "Loan Endpoints")
public class LoanApi {
    @Autowired
    LoanService loanService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid CreateLoanDTO dto) {
        String id = loanService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping
    public ResponseEntity<List<LoanResponseDTO>> getAll() {
        List<LoanResponseDTO> loanList = loanService.getAll();
        return ResponseEntity.ok().body(loanList);
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<LoanResponseDTO>> getAllOverdue() {
        List<LoanResponseDTO> loanList = loanService.getAllOverdue();
        return ResponseEntity.ok().body(loanList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponseDTO> getById(@PathVariable @Parameter(description = "ULID Type") String id) {
        LoanResponseDTO loan = loanService.getById(id);
        return ResponseEntity.ok().body(loan);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LoanResponseDTO> update(@PathVariable @Parameter(description = "ULID Type") String id, @RequestBody @Valid UpdateLoanDTO dto) {
        LoanResponseDTO loan = loanService.update(id, dto);
        return ResponseEntity.ok().body(loan);
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<LoanResponseDTO> returnLoan(@PathVariable @Parameter(description = "ULID Type") String id) {
        LoanResponseDTO loan = loanService.returnLoan(id);
        return ResponseEntity.ok().body(loan);
    }
}
