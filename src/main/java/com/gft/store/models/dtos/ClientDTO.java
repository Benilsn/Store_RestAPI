package com.gft.store.models.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gft.store.models.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    @JsonIgnore
    private Long id;

    private String cpf;

    private String client_name;

    private String phone;

    private String email;

    private Address address;
}
