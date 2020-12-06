package com.dbproject.startupspace.repository;

import com.dbproject.startupspace.domain.entity.MetropolitanCenterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetropolitanCenterEntityRepository extends JpaRepository<MetropolitanCenterEntity, Integer> {
}
