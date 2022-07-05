package com.gft.store.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Long id;

    private String street_name;

    private Integer address_number;

    private String complement;

    private String zip_code;

    private String district;

    private String county;

    private String estate;

}
