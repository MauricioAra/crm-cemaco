package com.softechfactory.crmcemaco.repository;

import com.softechfactory.crmcemaco.domain.Origin;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Origin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OriginRepository extends JpaRepository<Origin, Long> {

}
