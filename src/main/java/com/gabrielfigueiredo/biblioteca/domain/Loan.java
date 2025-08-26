package com.gabrielfigueiredo.biblioteca.domain;

import com.gabrielfigueiredo.biblioteca.dto.loanDTOs.CreateLoanDTO;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Loan {
    private String id;
    private Timestamp rentDate;
    private Timestamp returnDate;
    private Timestamp dueDate;

    private String clientId;
    private String inventoryId;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Loan(String id, CreateLoanDTO dto) {
        this.id = id;
        this.rentDate = dto.rentDate();
        this.dueDate = dto.dueDate();

        this.clientId = dto.clientId();
        this.inventoryId = dto.inventoryId();
    }
}
