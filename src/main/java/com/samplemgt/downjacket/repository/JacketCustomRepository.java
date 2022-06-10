package com.samplemgt.downjacket.repository;

import com.samplemgt.downjacket.dto.CustomJacketReq;
import com.samplemgt.downjacket.entity.Jacket;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface JacketCustomRepository {

    public List<Jacket> findJacketByProperties(String keyWord, String status, Date from, Date to);

    public List<Jacket> findJacketByProperties(String keyWord, Date from, Date to);

    public List<Jacket> findJacketByProperties(Date from, Date to);

    public List<Jacket> findJacketByProperties(CustomJacketReq req);
}
