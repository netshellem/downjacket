package com.samplemgt.downjacket.service;

import com.samplemgt.downjacket.entity.JacketStatus;

import java.util.List;

public interface JacketStatusService {

    public List<JacketStatus> findAllJacketStatus();
    public JacketStatus save(JacketStatus status);

    public JacketStatus getDefaultStatus();
}
