package com.samplemgt.downjacket.repository;


import com.samplemgt.downjacket.entity.JacketStatus;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JacketStatusRepository extends MongoRepository<JacketStatus, String> {

}
