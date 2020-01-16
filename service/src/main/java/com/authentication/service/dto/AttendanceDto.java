package com.authentication.service.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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

    @DateTimeFormat(iso = ISO.DATE)
    private Date date;

    private boolean isDeleted;

    private UserDto user;

}
