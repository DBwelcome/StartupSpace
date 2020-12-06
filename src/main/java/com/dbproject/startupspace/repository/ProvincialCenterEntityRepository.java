package com.dbproject.startupspace.repository;

import com.dbproject.startupspace.domain.entity.ProvincialCenterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvincialCenterEntityRepository extends JpaRepository<ProvincialCenterEntity, Integer> {
}
