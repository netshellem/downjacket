package com.samplemgt.downjacket.service.impl;

import com.samplemgt.downjacket.entity.JacketType;
import com.samplemgt.downjacket.repository.JacketTypeRepository;
import com.samplemgt.downjacket.service.JacketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JacketTypeServiceImpl implements JacketTypeService {

    @Autowired
    JacketTypeRepository jacketTypeRepository;

    @Override
    public JacketType save(JacketType type) {
        return jacketTypeRepository.save(type);
    }

    @Override
    public List<JacketType> findAllJacketType(){
        return jacketTypeRepository.findAll();
    }
}
