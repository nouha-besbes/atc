package com.authentication.service.dto;

import java.util.Date;

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
public class AttendanceDto {

    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "name cannot be null")
    private String name;

    private Date date;

    private boolean isDeleted;

    private UserDto userDto;

}
