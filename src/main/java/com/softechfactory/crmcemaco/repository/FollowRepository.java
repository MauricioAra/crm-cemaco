package com.softechfactory.crmcemaco.repository;

import com.softechfactory.crmcemaco.domain.Follow;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the Follow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByNextContactDate(String date);
    List<Follow> findAllByRegistryDate(String date);
}
