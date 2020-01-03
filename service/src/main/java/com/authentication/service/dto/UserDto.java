package com.authentication.service.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDto {

    private Long id;

    @NotBlank(message = "firstName cannot be null")
    private String firstName;

    @NotBlank(message = "lastName cannot be null")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "e-mail is required")
    private String email;

    public UserDto() {
        super();
    }

    public UserDto(String firstName, String lastName, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
