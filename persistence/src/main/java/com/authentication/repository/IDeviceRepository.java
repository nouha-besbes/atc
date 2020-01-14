package com.authentication.repository;

import java.util.Optional;

import com.authentication.model.Device;

public interface IDeviceRepository extends IBaseRepository<Device, Long> {

    Optional<Device> findByIpAdressAndPort(String ipAdress, Integer port);

}
