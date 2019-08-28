package com.example.demo.repository.mysql;

import com.example.demo.domain.po.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Long>,
        JpaSpecificationExecutor<Workshop>, QuerydslPredicateExecutor<Workshop> {
}