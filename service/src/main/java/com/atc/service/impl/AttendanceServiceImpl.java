package com.atc.service.impl;

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

import com.atc.dto.UserAttendanceDto;
import com.atc.model.Attendance;
import com.atc.model.User;
import com.atc.repository.IAttendanceRepository;
import com.atc.repository.IUserRepository;
import com.atc.service.IAttendanceService;
import com.atc.service.dto.AttendanceDto;
import com.atc.service.dto.UserAttendanceDateDto;
import com.atc.service.exception.MethodNotAllowedException;
import com.atc.service.exception.ResourceNotFoundException;
import com.atc.service.util.DateConverter;

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

        calculateWorkedTimeInEveryDayForEachUser(usersAttendance);

        List<UserAttendanceDateDto> userAttendancesDate = usersAttendance.stream()
                .map(attendance -> modelMapper.map(attendance, UserAttendanceDateDto.class))
                .collect(Collectors.toList());

        return userAttendancesDate;
    }

    private void calculateWorkedTimeInEveryDayForEachUser(List<UserAttendanceDto> usersAttendance) {
        usersAttendance.forEach(userAttendance -> {
            int numberOfAttendanceToCalculate = userAttendance.getAttendances().size();
            // Calculate only pair attendance
            if (numberOfAttendanceToCalculate % 2 != 0 && numberOfAttendanceToCalculate > 0) {
                numberOfAttendanceToCalculate--;
            }

            calculateWorkekTimeByUserInOneDay(userAttendance, numberOfAttendanceToCalculate);
        });
    }

    private void calculateWorkekTimeByUserInOneDay(UserAttendanceDto userAttendance,
            int numberOfAttendanceToCalculate) {
        for (int i = 0; i < numberOfAttendanceToCalculate; i++) {
            Date firstDate = userAttendance.getAttendances().get(i).getDate();
            Date secondDate = userAttendance.getAttendances().get(i + 1).getDate();
            long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
            userAttendance.setWorkedTime(userAttendance.getWorkedTime() + diffInMillies);
            i++;
        }
    }

}
