package com.samplemgt.downjacket.repository;

import com.samplemgt.downjacket.entity.Jacket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface JacketRepository extends MongoRepository<Jacket, String> {

    public List<Jacket> findByCreateDateBetween(Date from, Date to);

    public Optional<Jacket> findByJacketId(String jacketId);

    public Boolean existsByJacketId(String jacketId);
}
