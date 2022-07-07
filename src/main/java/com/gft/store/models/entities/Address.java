package com.gft.store.models.entities;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Street name invalid!")
    private String street_name;

    @Min(value = 1, message = "Number must be at least 1")
    private Integer address_number;

    private String complement;

    @NotEmpty(message = "Zip code invalid!")
    private String zip_code;

    @NotEmpty(message = "District name invalid!")
    private String district;

    @NotEmpty(message = "County invalid!")
    private String county;

    @NotEmpty(message = "Estate name invalid!")
    private String estate;

}
