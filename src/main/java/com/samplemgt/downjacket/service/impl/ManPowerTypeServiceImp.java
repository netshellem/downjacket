package com.samplemgt.downjacket.service.impl;

import com.samplemgt.downjacket.entity.ManPowerType;
import com.samplemgt.downjacket.repository.ManPowerTypeRepository;
import com.samplemgt.downjacket.service.ManPowerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManPowerTypeServiceImp implements ManPowerTypeService {

    @Autowired
    ManPowerTypeRepository manPowerTypeRepository;

    @Override
    public List<ManPowerType> findAllManPowerType() {
        return manPowerTypeRepository.findAll();
    }

    @Override
    public ManPowerType save(ManPowerType type) {
        return manPowerTypeRepository.save(type);
    }
}
