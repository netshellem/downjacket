package com.samplemgt.downjacket.service.impl;

import com.samplemgt.downjacket.entity.Season;
import com.samplemgt.downjacket.repository.SeasonRepository;
import com.samplemgt.downjacket.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeasonServiceImpl implements SeasonService {

    @Autowired
    SeasonRepository seasonRepository;

    @Override
    public Season save(Season season) {
        return seasonRepository.save(season);
    }

    @Override
    public Season findOneSeason() {
        return seasonRepository.findAll().get(0);
    }
}
