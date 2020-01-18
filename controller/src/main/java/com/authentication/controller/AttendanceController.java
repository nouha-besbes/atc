package com.authentication.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.controller.utils.ApiPageable;
import com.authentication.service.IAttendanceService;
import com.authentication.service.dto.AttendanceDto;
import com.authentication.service.dto.UserAttendanceDateDto;
import com.authentication.service.exception.ResourceNotFoundException;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1")
public class AttendanceController {

    @Autowired
    private IAttendanceService attendanceService;

    @PostMapping("/attendances")
    public AttendanceDto createAttendance(@Valid @RequestBody AttendanceDto attendanceDto)
            throws ResourceNotFoundException {
        return attendanceService.save(attendanceDto);
    }

    @PutMapping("/attendances/{id}")
    public ResponseEntity<AttendanceDto> updateAttendance(@PathVariable(value = "id") Long attendanceId,
            @Valid @RequestBody AttendanceDto attendanceDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(attendanceService.update(attendanceId, attendanceDetails));
    }

    @GetMapping("/attendances/{id}")
    public ResponseEntity<AttendanceDto> getAttendanceById(@PathVariable(value = "id") Long attendanceId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(attendanceService.findDtoById(attendanceId));
    }

    @DeleteMapping("/attendances/{id}")
    public Map<String, Boolean> deleteAttendance(@PathVariable(value = "id") Long attendanceId) throws Exception {
        attendanceService.deleteById(attendanceId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @ApiPageable
    @GetMapping("/attendances")
    public Page<AttendanceDto> getAllAttendances(@ApiIgnore Pageable pageable) {
        return attendanceService.findAll(pageable);
    }

    @ApiPageable
    @GetMapping("/attendances/User/")
    public Page<AttendanceDto> getAttendancesByUserId(@PathVariable(value = "id") Long userId,
            @ApiIgnore Pageable pageable) {
        return attendanceService.findByUserId(userId, pageable);
    }

    @GetMapping("/attendances/{userIds}/{startDate}/{endDate}")
    public List<AttendanceDto> getAttendancesByFilter(@RequestParam Long[] userIds,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return attendanceService.findAttendanceByUserBetweenDates(startDate, endDate, userIds);
    }

    @GetMapping("/attendances/calculate/{userIds}/{startDate}/{endDate}")
    public List<UserAttendanceDateDto> calculteAttendancesByFilter(@RequestParam Long[] userIds,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        // 2020-01-15T20:49:53.067Z
        return attendanceService.calculateAttendanceByUserBetweenDates(startDate, endDate, userIds);
    }

}
