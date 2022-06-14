package com.samplemgt.downjacket.repository;


import com.samplemgt.downjacket.entity.JacketStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface JacketStatusRepository extends MongoRepository<JacketStatus, String> {

    public Optional<JacketStatus> findByIndex(String index);
}
