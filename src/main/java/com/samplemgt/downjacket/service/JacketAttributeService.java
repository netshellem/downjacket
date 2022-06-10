package com.samplemgt.downjacket.service;

import com.samplemgt.downjacket.entity.JacketAttribute;

import java.util.List;

public interface JacketAttributeService {

    public List<JacketAttribute> findAllJacketAttribute();
    public JacketAttribute save(JacketAttribute attribute);
}
