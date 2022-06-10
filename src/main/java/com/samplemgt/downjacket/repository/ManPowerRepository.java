package com.samplemgt.downjacket.repository;

import com.samplemgt.downjacket.entity.ManPower;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ManPowerRepository  extends MongoRepository<ManPower, String> {

    @Query("{type:'?0'}")
    List<ManPower> findManPowerByType(String type);

    boolean existsByName(String name);

    ManPower deleteByName(String name);
}
