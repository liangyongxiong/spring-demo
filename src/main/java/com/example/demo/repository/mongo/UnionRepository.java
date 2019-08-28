package com.example.demo.repository.mongo;

import com.example.demo.domain.po.Union;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UnionRepository extends MongoRepository<Union, ObjectId>, QuerydslPredicateExecutor<Union> {
}