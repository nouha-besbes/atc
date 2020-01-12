package com.authentication.service.dto;

import javax.validation.constraints.NotBlank;

import com.authentication.utils.DeviceType;

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
public class DeviceDto {

    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "ipAdress cannot be null")
    private String ipAdress;

    @NotBlank(message = "port cannot be null")
    private String port;

    @NotBlank(message = "deviceType cannot be null")
    private DeviceType deviceType;

    private AffiliateDto affiliateDto;

}
