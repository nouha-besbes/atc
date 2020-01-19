package com.atc.repository;

import java.util.Optional;

import com.atc.model.Device;

public interface IDeviceRepository extends IBaseRepository<Device, Long> {

    Optional<Device> findByIpAdressAndPort(String ipAdress, Integer port);

    boolean existsByAffiliateId(Long affiliateId);

}
