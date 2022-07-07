package com.gft.store.models.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "CPF Cannot be empty!")
    @CPF
    private String cpf;

    @NotEmpty(message = "Client name cannot be empty!")
    private String client_name;

    @NotEmpty(message = "Phone cannot be empty!")
    private String phone;

    @NotEmpty(message = "Email cannot be empty!")
    @Email(message = "E-mail invalid!")
    private String email;

    @Embedded
    @Valid
    private Address address;

}
