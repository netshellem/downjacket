package com.samplemgt.downjacket.service.impl;

import com.samplemgt.downjacket.entity.ManPower;
import com.samplemgt.downjacket.repository.ManPowerRepository;
import com.samplemgt.downjacket.service.ManPowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManPowerServiceImpl implements ManPowerService {

    @Autowired
    ManPowerRepository manPowerRepository;
    @Override
    public List<ManPower> findAllManPower() {
        return manPowerRepository.findAll();
    }

    @Override
    public List<ManPower> findAllManPowerByType(String type) {
        return manPowerRepository.findManPowerByType(type);
    }

    @Override
    public ManPower save(ManPower man) {
        return manPowerRepository.save(man);
    }

    @Override
    public void delete(ManPower man) {
        manPowerRepository.delete(man);
    }

    @Override
    public boolean existsByName(String name){
       return manPowerRepository.existsByName(name);
    }

    @Override
    public ManPower deleteByName(String name){

        return manPowerRepository.deleteByName(name);
    }
}
