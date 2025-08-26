package com.gabrielfigueiredo.biblioteca.repository;

import com.gabrielfigueiredo.biblioteca.repository.interfaces.ILoanRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LoanRepository {
    private final Map<String, ILoanRepository> repositories;
    private final String selectedDB;

    public LoanRepository(
            Map<String, ILoanRepository> repositories,
            @Value("${selectedDB}") String selectedDB
    ) {
        this.repositories = repositories;
        this.selectedDB = selectedDB;
    }

    public ILoanRepository getRepository() {
        String key = selectedDB + "Loan";
        ILoanRepository repository = repositories.get(key);
        if (repository == null) {
            throw new IllegalArgumentException("Repositório não encontrado: " + key);
        }
        return repository;
    }
}
