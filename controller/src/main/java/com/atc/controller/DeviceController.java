package com.atc.controller;

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

import com.atc.service.IDeviceService;
import com.atc.service.dto.DeviceDto;
import com.atc.service.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class DeviceController {

    @Autowired
    private IDeviceService deviceService;

    @PostMapping("/devices")
    public DeviceDto createDevice(@Valid @RequestBody DeviceDto deviceDto) throws ResourceNotFoundException {
        return deviceService.save(deviceDto);
    }

    @PutMapping("/devices/{id}")
    public ResponseEntity<DeviceDto> updateDevice(@PathVariable(value = "id") Long deviceId,
            @Valid @RequestBody DeviceDto deviceDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(deviceService.update(deviceId, deviceDetails));
    }

    @GetMapping("/devices/{id}")
    public ResponseEntity<DeviceDto> getDeviceById(@PathVariable(value = "id") Long deviceId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(deviceService.findDtoById(deviceId));
    }

    @DeleteMapping("/devices/{id}")
    public Map<String, Boolean> deleteDevice(@PathVariable(value = "id") Long deviceId) throws Exception {
        deviceService.deleteById(deviceId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/devices")
    public List<DeviceDto> getAllDevices() {
        return deviceService.findAll();
    }

}
