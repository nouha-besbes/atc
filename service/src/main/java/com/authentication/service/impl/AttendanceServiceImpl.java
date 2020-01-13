package com.authentication.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authentication.model.Attendance;
import com.authentication.model.User;
import com.authentication.repository.IAttendanceRepository;
import com.authentication.repository.IUserRepository;
import com.authentication.service.IAttendanceService;
import com.authentication.service.dto.AttendanceDto;
import com.authentication.service.exception.ResourceNotFoundException;

@Service
public class AttendanceServiceImpl implements IAttendanceService {

    @Autowired
    private IAttendanceRepository attendanceRepository;

    @Autowired
    private IUserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public AttendanceDto save(@Valid AttendanceDto attendanceDto) throws ResourceNotFoundException {
        Attendance attendance = modelMapper.map(attendanceDto, Attendance.class);
        User user = userRepository.findById(attendanceDto.getUserDto().getId()).orElseThrow(
                () -> new ResourceNotFoundException("User not found on :: " + attendanceDto.getUserDto().getId()));
        attendance.setUser(user);
        return modelMapper.map(attendanceRepository.save(attendance), AttendanceDto.class);
    }

    @Override
    public AttendanceDto updateAttendance(Long attendanceId, @Valid AttendanceDto attendanceDetails)
            throws ResourceNotFoundException {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found on :: " + attendanceId));
        User user = userRepository.findById(attendanceDetails.getUserDto().getId()).orElseThrow(
                () -> new ResourceNotFoundException("User not found on :: " + attendanceDetails.getUserDto().getId()));

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
    public void deleteById(Long attendanceId) throws ResourceNotFoundException {
        Optional.of(attendanceRepository.existsById(attendanceId))
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found on :: " + attendanceId));
        attendanceRepository.deleteById(attendanceId);
    }

    @Override
    public List<AttendanceDto> findAll() {
        List<Attendance> attendances = attendanceRepository.findAll();
        return attendances.stream().map(attendence -> modelMapper.map(attendence, AttendanceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDto> findByUserId(Long userId) {
        List<Attendance> attendances = attendanceRepository.findAttendanceByUserId(userId);
        return attendances.stream().map(attendence -> modelMapper.map(attendence, AttendanceDto.class))
                .collect(Collectors.toList());
    }

}
