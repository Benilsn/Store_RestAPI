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

import org.hibernate.validator.constraints.br.CNPJ;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_providers")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CNPJ
    @NotEmpty(message = "CNPJ Cannot be empty!")
    private String cnpj;

    @NotEmpty(message = "Provider name cannot be empty!")
    private String provider_name;

    @NotEmpty(message = "Phone cannot be empty!")
    private String phone;

    @NotEmpty(message = "E-mail cannot be empty!")
    @Email(message = "E-mail invalid!")
    private String email;

    @Embedded
    @Valid
    private Address address;
}
