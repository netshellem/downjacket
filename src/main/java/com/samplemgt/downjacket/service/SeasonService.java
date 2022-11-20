package com.samplemgt.downjacket.service;


import com.samplemgt.downjacket.entity.Season;

public interface SeasonService {
    public Season save(Season season);
    public Season findOneSeason();
}
