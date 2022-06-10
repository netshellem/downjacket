package com.samplemgt.downjacket.repository;

import com.samplemgt.downjacket.entity.ManPowerType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ManPowerTypeRepository extends MongoRepository<ManPowerType, String> {
}
