package com.gabrielfigueiredo.biblioteca.domain;

import com.gabrielfigueiredo.biblioteca.dto.clientDTOs.CreateClientDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Client {
    @Schema(description = "ULID")
    private String id;
    private String name;
    private String phone;
    private String email;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Client(String id, CreateClientDTO dto, String phone) {
        this.id = id;
        this.name = dto.name();
        this.phone = phone;
        this.email = dto.email();
    }
}
