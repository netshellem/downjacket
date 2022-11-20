package com.samplemgt.downjacket.repository;

import com.samplemgt.downjacket.entity.Season;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SeasonRepository extends MongoRepository<Season, String> {
}
