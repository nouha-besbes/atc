package com.authentication.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.authentication.dto.UserAttendanceDto;
import com.authentication.model.Attendance;
import com.authentication.model.User;
import com.authentication.repository.IAttendanceRepository;
import com.authentication.repository.IUserRepository;
import com.authentication.service.IAttendanceService;
import com.authentication.service.dto.AttendanceDto;
import com.authentication.service.dto.UserAttendanceDateDto;
import com.authentication.service.exception.MethodNotAllowedException;
import com.authentication.service.exception.ResourceNotFoundException;
import com.authentication.service.util.DateConverter;

@Service
public class AttendanceServiceImpl implements IAttendanceService {

    private final IAttendanceRepository attendanceRepository;

    private final IUserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public AttendanceServiceImpl(IAttendanceRepository attendanceRepository, IUserRepository userRepository) {
        super();
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AttendanceDto save(@Valid AttendanceDto attendanceDto) throws ResourceNotFoundException {
        Attendance attendance = modelMapper.map(attendanceDto, Attendance.class);
        User user = userRepository.findById(attendanceDto.getUser().getId()).orElseThrow(
                () -> new ResourceNotFoundException("User not found on :: " + attendanceDto.getUser().getId()));
        attendance.setUser(user);
        return modelMapper.map(attendanceRepository.save(attendance), AttendanceDto.class);
    }

    @Override
    public AttendanceDto update(Long attendanceId, @Valid AttendanceDto attendanceDetails)
            throws ResourceNotFoundException {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found on :: " + attendanceId));
        User user = userRepository.findById(attendanceDetails.getUser().getId()).orElseThrow(
                () -> new ResourceNotFoundException("User not found on :: " + attendanceDetails.getUser().getId()));

        attendance.setId(attendanceId);
        attendance.setDate(attendanceDetails.getDate());
        attendance.setUser(user);
        attendanceRepository.save(attendance);
        attendanceDetails.setId(attendanceId);
        return attendanceDetails;
    }

    @Override
    public AttendanceDto findDtoById(Long attendanceId) {
        return modelMapper.map(attendanceRepository.findById(attendanceId).get(), AttendanceDto.class);
    }

    @Override
    public void deleteById(Long attendanceId) throws ResourceNotFoundException, MethodNotAllowedException {
        if (!attendanceRepository.existsById(attendanceId)) {
            throw new ResourceNotFoundException("Attendance not found on :: " + attendanceId);
        }
        attendanceRepository.deleteById(attendanceId);
    }

    @Override
    public Page<AttendanceDto> findAll(Pageable pageable) {
        Page<Attendance> attendancesPage = attendanceRepository.findAll(pageable);
        List<AttendanceDto> attendances = attendancesPage.getContent().stream()
                .map(attendence -> modelMapper.map(attendence, AttendanceDto.class)).collect(Collectors.toList());
        return new PageImpl<AttendanceDto>(attendances, attendancesPage.getPageable(), attendances.size());
    }

    @Override
    public Page<AttendanceDto> findByUserId(Long userId, Pageable pageble) {
        Page<Attendance> attendancesPage = attendanceRepository.findAttendanceByUserId(userId, pageble);
        List<AttendanceDto> attendances = attendancesPage.getContent().stream()
                .map(attendence -> modelMapper.map(attendence, AttendanceDto.class)).collect(Collectors.toList());
        return new PageImpl<AttendanceDto>(attendances, attendancesPage.getPageable(), attendances.size());
    }

    @Override
    public List<AttendanceDto> findAttendanceByUserBetweenDates(Date startDate, Date endDate, Long[] userIds) {
        List<Attendance> attendances = attendanceRepository.findAttendanceByUserBetweenDates(userIds, startDate,
                endDate);
        List<AttendanceDto> attendancesDto = attendances.stream()
                .map(attendence -> modelMapper.map(attendence, AttendanceDto.class)).collect(Collectors.toList());
        return attendancesDto;
    }

    @Override
    public List<UserAttendanceDateDto> calculateAttendanceByUserBetweenDates(Date startDate, Date endDate,
            Long[] userIds) {
        List<Date> dates = DateConverter.extractDatesBetweenTowDates(startDate, endDate);
        List<UserAttendanceDto> usersAttendance = attendanceRepository.findAttendanceGroupedByUserAndDate(userIds,
                dates);
        usersAttendance.forEach(userDate -> userDate.setAttendances(
                attendanceRepository.findAttendanceByUserIdAndDate(userDate.getUser().getId(), userDate.getDate())));

        return usersAttendance.stream().map(attendance -> modelMapper.map(attendance, UserAttendanceDateDto.class))
                .collect(Collectors.toList());
    }

}
