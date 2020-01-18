package com.authentication.service;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.authentication.service.dto.AttendanceDto;
import com.authentication.service.dto.UserAttendanceDateDto;
import com.authentication.service.exception.MethodNotAllowedException;
import com.authentication.service.exception.ResourceNotFoundException;

public interface IAttendanceService {

    AttendanceDto save(@Valid AttendanceDto attendenceDto) throws ResourceNotFoundException;

    AttendanceDto update(Long attendanceId, @Valid AttendanceDto attendanceDetails) throws ResourceNotFoundException;

    AttendanceDto findDtoById(Long attendenceId);

    void deleteById(Long attendenceId) throws ResourceNotFoundException, MethodNotAllowedException;

    Page<AttendanceDto> findAll(Pageable pageable);

    Page<AttendanceDto> findByUserId(Long userId, Pageable pageable);

    List<UserAttendanceDateDto> calculateAttendanceByUserBetweenDates(Date startDate, Date endDate, Long[] userIds);

    List<AttendanceDto> findAttendanceByUserBetweenDates(Date startDate, Date endDate, Long[] userIds);

}
