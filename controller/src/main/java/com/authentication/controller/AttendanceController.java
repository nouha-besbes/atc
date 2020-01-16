package com.authentication.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.service.IAttendanceService;
import com.authentication.service.dto.AttendanceDto;
import com.authentication.service.exception.ResourceNotFoundException;

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

    @GetMapping("/attendances")
    public List<AttendanceDto> getAllAttendances() {
        return attendanceService.findAll();
    }

    @GetMapping("/attendances/User/")
    public List<AttendanceDto> getAttendancesByUserId(@PathVariable(value = "id") Long userId) {
        return attendanceService.findByUserId(userId);
    }

}
