package com.gft.store.models.dtos;

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
public class ProviderDTO {

    @JsonIgnore
    private Long id;

    private String cnpj;

    private String provider_name;

    private String phone;

    private String email;

    private Address address;
}
