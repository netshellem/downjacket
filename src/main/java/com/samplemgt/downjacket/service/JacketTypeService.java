package com.samplemgt.downjacket.service;

import com.samplemgt.downjacket.entity.JacketType;

import java.util.List;

public interface JacketTypeService {
    public JacketType save(JacketType type);

    public List<JacketType> findAllJacketType();
}
