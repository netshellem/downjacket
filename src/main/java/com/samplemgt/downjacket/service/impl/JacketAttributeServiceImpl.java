package com.samplemgt.downjacket.service.impl;

import com.samplemgt.downjacket.entity.JacketAttribute;
import com.samplemgt.downjacket.repository.JacketAttributeRepository;
import com.samplemgt.downjacket.service.JacketAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JacketAttributeServiceImpl implements JacketAttributeService {

    @Autowired
    JacketAttributeRepository jacketAttributeRepository;

    @Override
    public List<JacketAttribute> findAllJacketAttribute() {
        return jacketAttributeRepository.findAll();
    }

    @Override
    public JacketAttribute save(JacketAttribute attribute) {
        return jacketAttributeRepository.save(attribute);
    }
}
