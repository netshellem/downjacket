package com.samplemgt.downjacket.repository;

import com.samplemgt.downjacket.entity.JacketType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JacketTypeRepository extends MongoRepository<JacketType, String> {
}
