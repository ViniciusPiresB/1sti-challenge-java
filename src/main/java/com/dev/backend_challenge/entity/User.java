package com.dev.backend_challenge.entity;

import com.dev.backend_challenge.enums.Status;
import com.dev.backend_challenge.enums.TypeUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "user")
public class User implements UserDetails {
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
    private TypeUser typeUser = TypeUser.USER;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority userRole = new SimpleGrantedAuthority("ROLE_USER");
        SimpleGrantedAuthority adminRole = new SimpleGrantedAuthority("ROLE_ADMIN");
        SimpleGrantedAuthority rootRole = new SimpleGrantedAuthority("ROLE_ROOT");

        if(this.typeUser == TypeUser.ROOT) return List.of(rootRole, adminRole, userRole);

        if(this.typeUser == TypeUser.ADMIN) return List.of(adminRole, userRole);

        return List.of(userRole);
    }

    @Override
    public String getUsername() {
        return this.cpf;
    }
}
