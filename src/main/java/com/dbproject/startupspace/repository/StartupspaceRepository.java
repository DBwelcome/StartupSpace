package com.dbproject.startupspace.repository;


import com.dbproject.startupspace.domain.entity.SpaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StartupspaceRepository extends JpaRepository<SpaceEntity, Long> {
}
