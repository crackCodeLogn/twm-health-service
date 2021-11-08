package com.vv.personal.twm.health.repository;

import com.vv.personal.twm.health.model.HealthCharterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vivek
 * @since 08/11/21
 */
@Repository
public interface HealthCharterRepository extends JpaRepository<HealthCharterEntity, String> {
}