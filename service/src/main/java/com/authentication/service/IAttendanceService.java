package com.authentication.service;

import java.util.List;

import javax.validation.Valid;

import com.authentication.service.dto.AttendanceDto;
import com.authentication.service.exception.ResourceNotFoundException;

public interface IAttendanceService {

    AttendanceDto save(@Valid AttendanceDto attendenceDto) throws ResourceNotFoundException;

    AttendanceDto updateAttendance(Long attendanceId, @Valid AttendanceDto attendanceDetails)
            throws ResourceNotFoundException;

    AttendanceDto findDtoById(Long attendenceId);

    void deleteById(Long attendenceId) throws ResourceNotFoundException;

    List<AttendanceDto> findAll();

    List<AttendanceDto> findByUserId(Long userId);

}
