package com.authentication.service;

import java.util.List;

import javax.validation.Valid;

import com.authentication.service.dto.DeviceDto;
import com.authentication.service.exception.ResourceNotFoundException;

public interface IDeviceService {

    DeviceDto save(@Valid DeviceDto deviceDto) throws ResourceNotFoundException;

    DeviceDto updateDevice(Long deviceId, @Valid DeviceDto deviceDto) throws ResourceNotFoundException;

    DeviceDto findDtoById(Long deviceId);

    void deleteById(Long deviceId) throws ResourceNotFoundException;

    List<DeviceDto> findAll();

}
