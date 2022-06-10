package com.samplemgt.downjacket.service.impl;

import com.samplemgt.downjacket.entity.JacketStatus;
import com.samplemgt.downjacket.repository.JacketStatusRepository;
import com.samplemgt.downjacket.service.JacketStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JacketStatusServiceImpl implements JacketStatusService {

    @Autowired
    private JacketStatusRepository jacketStatusRepository;

    @Override
    public List<JacketStatus> findAllJacketStatus() {
        return jacketStatusRepository.findAll();
    }

    @Override
    public JacketStatus save(JacketStatus status) {
        return jacketStatusRepository.save(status);
    }
}
