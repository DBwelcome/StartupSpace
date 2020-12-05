package com.dbproject.startupspace.repository;

import com.dbproject.startupspace.domain.dto.CenterDTO;
import com.dbproject.startupspace.domain.entity.MetropolitanCenterEntity;
import com.dbproject.startupspace.domain.entity.ProvincialCenterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvincialCenterEntityRepository extends JpaRepository<ProvincialCenterEntity, Integer> {
}
