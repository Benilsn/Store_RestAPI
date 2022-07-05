package com.gft.store.models.dtos;

import com.gft.store.models.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO {

    private Long id;

    private String branch_name;

    private Address address;
}
