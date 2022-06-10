package com.samplemgt.downjacket.service;

import com.samplemgt.downjacket.entity.ManPower;

import java.util.List;

public interface ManPowerService {

    public List<ManPower> findAllManPower();
    public List<ManPower> findAllManPowerByType(String type);
    public ManPower save(ManPower man);
    public void delete(ManPower man);
    public boolean existsByName(String name);

    public ManPower deleteByName(String name);
}
