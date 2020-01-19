package com.atc.service.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDto {

    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "firstName cannot be null")
    private String firstName;

    @NotBlank(message = "lastName cannot be null")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "e-mail is required")
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "affiliate is required")
    private AffiliateDto affiliate;

}
