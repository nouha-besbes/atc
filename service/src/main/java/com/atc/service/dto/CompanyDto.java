package com.atc.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.atc.service.annotation.UniqueCompany;

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
public class CompanyDto {

    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "name cannot be null")
    @Size(max = 64)
    @UniqueCompany
    private String name;

}
