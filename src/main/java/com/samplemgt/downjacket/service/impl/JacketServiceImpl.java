package com.samplemgt.downjacket.service.impl;

import com.samplemgt.downjacket.entity.Jacket;
import com.samplemgt.downjacket.repository.JacketRepository;
import com.samplemgt.downjacket.service.JacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class JacketServiceImpl implements JacketService {

    @Autowired
    private JacketRepository jacketRepository;

    @Override
    public List<Jacket> findJacketByYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        Date start = cal.getTime();

        //set date to last day of 2014
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, 11); // 11 = december
        cal.set(Calendar.DAY_OF_MONTH, 31); // new years eve
        Date end = cal.getTime();

        return jacketRepository.findByCreateDateBetween(start,end);
    }

    @Override
    public Jacket save(Jacket jacket) {
        if(jacket.id != null){
            //update logic
        } else{
            //create logic
        }
        return jacketRepository.save(jacket);
    }

    @Override
    public void delete(Jacket jacket) {

        jacketRepository.delete(jacket);
    }

    @Override
    public void delete(String jacketId) {
        jacketRepository.delete(jacketRepository.findByJacketId(jacketId).get());
    }

    @Override
    public Boolean existsByJacketId(String jacketID){

        return jacketRepository.existsByJacketId(jacketID);
    }

}
