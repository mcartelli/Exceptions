package com.santander.exceptions.demo.repository;

import com.santander.exceptions.demo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMongoRepository extends MongoRepository<User,Long> {
}
