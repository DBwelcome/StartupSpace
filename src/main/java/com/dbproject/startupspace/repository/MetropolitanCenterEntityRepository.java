package com.dbproject.startupspace.repository;

import com.dbproject.startupspace.domain.dto.CenterDTO;
import com.dbproject.startupspace.domain.entity.MetropolitanCenterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MetropolitanCenterEntityRepository extends JpaRepository<MetropolitanCenterEntity, Integer> {
}
