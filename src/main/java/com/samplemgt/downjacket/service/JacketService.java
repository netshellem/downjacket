package com.samplemgt.downjacket.service;

import com.samplemgt.downjacket.dto.CustomJacketReq;
import com.samplemgt.downjacket.entity.Jacket;

import java.util.Date;
import java.util.List;

public interface JacketService {

    public List<Jacket> findJacketByYear(int year);

    public Jacket save(Jacket jacket);
    public void delete(Jacket jacket);
    public void delete(String jacketId);
    public Boolean existsByJacketId(String jacketId);
    public Jacket getFirstJacket();
}
