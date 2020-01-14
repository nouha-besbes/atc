package com.authentication.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authentication.model.Affiliate;
import com.authentication.model.Device;
import com.authentication.repository.IAffiliateRepository;
import com.authentication.repository.IDeviceRepository;
import com.authentication.service.IDeviceService;
import com.authentication.service.dto.DeviceDto;
import com.authentication.service.exception.ResourceNotFoundException;

@Service
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private IDeviceRepository deviceRepository;

    @Autowired
    private IAffiliateRepository affiliateRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public DeviceDto save(@Valid DeviceDto deviceDto) throws ResourceNotFoundException {
        Device device = modelMapper.map(deviceDto, Device.class);
        Affiliate affiliate = affiliateRepository.findById(deviceDto.getAffiliateDto().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Affiliate not found on :: " + deviceDto.getAffiliateDto().getId()));
        device.setAffiliate(affiliate);
        return modelMapper.map(deviceRepository.save(device), DeviceDto.class);
    }

    @Override
    public DeviceDto updateDevice(Long deviceId, @Valid DeviceDto deviceDetails) throws ResourceNotFoundException {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found on :: " + deviceId));
        Affiliate affiliate = affiliateRepository.findById(deviceDetails.getAffiliateDto().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Company not found on :: " + deviceDetails.getAffiliateDto().getId()));

        device.setDeviceType(deviceDetails.getDeviceType());
        device.setIpAdress(deviceDetails.getIpAdress());
        device.setPort(Integer.valueOf(deviceDetails.getPort()));
        device.setId(deviceId);
        device.setAffiliate(affiliate);
        deviceRepository.save(device);
        deviceDetails.setId(deviceId);
        return deviceDetails;
    }

    @Override
    public DeviceDto findDtoById(Long deviceId) {
        return modelMapper.map(deviceRepository.findById(deviceId).get(), DeviceDto.class);
    }

    @Override
    public void deleteById(Long deviceId) throws ResourceNotFoundException {
        Optional.of(deviceRepository.existsById(deviceId))
                .orElseThrow(() -> new ResourceNotFoundException("device not found on :: " + deviceId));
        deviceRepository.deleteById(deviceId);
    }

    @Override
    public List<DeviceDto> findAll() {
        List<Device> devices = deviceRepository.findAll();
        return devices.stream().map(company -> modelMapper.map(company, DeviceDto.class)).collect(Collectors.toList());
    }

}
