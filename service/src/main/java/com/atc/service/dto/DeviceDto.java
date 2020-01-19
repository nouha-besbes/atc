package com.atc.service.dto;

import javax.validation.constraints.NotBlank;

import com.atc.service.annotation.UniqueDeviceIpAdressAndPort;
import com.atc.util.DeviceType;

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
@UniqueDeviceIpAdressAndPort(ipAdressField = "ipAdress", portField = "port")
public class DeviceDto {

    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "ipAdress cannot be null")
    private String ipAdress;

    @NotBlank(message = "port cannot be null")
    private String port;

    private DeviceType deviceType;

    private AffiliateDto affiliate;

}
