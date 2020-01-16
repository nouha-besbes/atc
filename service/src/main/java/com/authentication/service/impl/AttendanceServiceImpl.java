package com.authentication.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.authentication.model.Attendance;
import com.authentication.model.User;
import com.authentication.repository.IAttendanceRepository;
import com.authentication.repository.IUserRepository;
import com.authentication.service.IAttendanceService;
import com.authentication.service.dto.AttendanceDto;
import com.authentication.service.exception.MethodNotAllowedException;
import com.authentication.service.exception.ResourceNotFoundException;

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
    public List<AttendanceDto> findAll(Pageable pageable) {
        Page<Attendance> attendances = attendanceRepository.findAll(pageable);
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
