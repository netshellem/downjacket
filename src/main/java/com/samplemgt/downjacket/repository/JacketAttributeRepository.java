package com.samplemgt.downjacket.repository;

import com.samplemgt.downjacket.entity.JacketAttribute;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JacketAttributeRepository extends MongoRepository<JacketAttribute, String> {
}
