package com.atc.service;

import java.util.List;

import javax.validation.Valid;

import com.atc.service.dto.DeviceDto;
import com.atc.service.exception.ResourceNotFoundException;

public interface IDeviceService {

    DeviceDto save(@Valid DeviceDto deviceDto) throws ResourceNotFoundException;

    DeviceDto update(Long deviceId, @Valid DeviceDto deviceDto) throws ResourceNotFoundException;

    DeviceDto findDtoById(Long deviceId);

    void deleteById(Long deviceId) throws ResourceNotFoundException;

    List<DeviceDto> findAll();

}
