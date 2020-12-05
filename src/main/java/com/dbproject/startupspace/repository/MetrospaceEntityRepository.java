package com.dbproject.startupspace.repository;

import com.dbproject.startupspace.domain.entity.MetropolitanCenterEntity;
import com.dbproject.startupspace.domain.entity.MetrospaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetrospaceEntityRepository extends JpaRepository<MetrospaceEntity, Integer> {
}
