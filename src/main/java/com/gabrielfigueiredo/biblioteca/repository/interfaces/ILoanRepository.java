package com.gabrielfigueiredo.biblioteca.repository.interfaces;

import com.gabrielfigueiredo.biblioteca.domain.Loan;
import com.gabrielfigueiredo.biblioteca.dto.loanDTOs.LoanResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ILoanRepository {
    void create(Loan loan);
    Optional<Loan> getById(String id);
    List<LoanResponseDTO> getAll();
    boolean update(Loan loan);
    boolean deleteById(String id);
    Optional<Loan> returnLoan(String id);
    List<Loan> getAllOverdue();
}
