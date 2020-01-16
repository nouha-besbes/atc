package com.authentication.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import com.authentication.service.dto.AttendanceDto;
import com.authentication.service.exception.MethodNotAllowedException;
import com.authentication.service.exception.ResourceNotFoundException;

public interface IAttendanceService {

    AttendanceDto save(@Valid AttendanceDto attendenceDto) throws ResourceNotFoundException;

    AttendanceDto update(Long attendanceId, @Valid AttendanceDto attendanceDetails) throws ResourceNotFoundException;

    AttendanceDto findDtoById(Long attendenceId);

    void deleteById(Long attendenceId) throws ResourceNotFoundException, MethodNotAllowedException;

    List<AttendanceDto> findAll(Pageable pageable);

    List<AttendanceDto> findByUserId(Long userId);

}
