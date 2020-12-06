package com.dbproject.startupspace.repository;

import com.dbproject.startupspace.domain.entity.MetrospaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MetrospaceEntityRepository extends JpaRepository<MetrospaceEntity, Integer> {
    @Query(value = "SELECT COUNT(*) FROM metrospace WHERE center_id = ? AND tenant='입주중'", nativeQuery = true)
    Integer findNotEmptySpaceCount(Integer centerId);
}
