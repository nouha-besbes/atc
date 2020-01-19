package com.atc.service.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceFiltreDto {

    @DateTimeFormat(iso = ISO.DATE)
    private Date startDate;

    @DateTimeFormat(iso = ISO.DATE)
    private Date endDate;

    private List<UserDto> users;
}
