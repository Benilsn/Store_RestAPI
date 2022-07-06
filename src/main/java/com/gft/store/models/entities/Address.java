package com.gft.store.models.entities;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

    private String street_name;

    private Integer address_number;

    private String complement;

    private String zip_code;

    private String district;

    private String county;

    private String estate;

}
