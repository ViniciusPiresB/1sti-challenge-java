package com.dev.backend_challenge.entity;

import com.dev.backend_challenge.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    @Column(unique = true)
    private String cpf;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotNull
    private LocalDate birth;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @NumberFormat
    @NotNull
    private Integer typeUser = 0;

    @CreationTimestamp
    private LocalDate createdAt;

    @NotBlank
    private String createdBy;

    @UpdateTimestamp
    private LocalDate updatedAt;

    private String updatedBy;

    private LocalDate deletedAt;

    private String deletedBy;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
