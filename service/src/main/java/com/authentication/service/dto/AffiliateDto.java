package com.authentication.service.dto;

import javax.validation.constraints.NotBlank;

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
    @UniqueAffiliate
    private String name;

    private CompanyDto companyDto;

}
