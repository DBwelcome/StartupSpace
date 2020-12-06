package com.dbproject.startupspace.repository;

import com.dbproject.startupspace.domain.entity.ProvspaceEntity;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Registered
public interface ProvspaceEntityRepository extends JpaRepository<ProvspaceEntity, Integer> {
    @Query(value = "SELECT COUNT(*) FROM provspace WHERE center_id = ? AND tenant='입주중'", nativeQuery = true)
    Integer findNotEmptySpaceCount(Integer centerId);
}
