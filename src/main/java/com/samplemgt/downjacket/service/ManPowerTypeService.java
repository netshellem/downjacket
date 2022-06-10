package com.samplemgt.downjacket.service;

import com.samplemgt.downjacket.entity.ManPowerType;

import java.util.List;

public interface ManPowerTypeService {

    public List<ManPowerType> findAllManPowerType();
    public ManPowerType save(ManPowerType type);
}
