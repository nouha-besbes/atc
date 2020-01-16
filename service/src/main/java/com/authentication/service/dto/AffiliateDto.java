package com.authentication.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.authentication.service.annotation.UniqueAffiliate;

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
public class AffiliateDto {

    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "name cannot be null")
    @Size(max = 64)
    @UniqueAffiliate
    private String name;

    private CompanyDto company;

}
