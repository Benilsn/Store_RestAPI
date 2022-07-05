package com.gft.store.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.gft.store.models.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserModelDTO {

    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    private String email;

    @JsonProperty(access = Access.READ_ONLY)
    private String user_password;

    private String full_name;

    private Role role;
}
