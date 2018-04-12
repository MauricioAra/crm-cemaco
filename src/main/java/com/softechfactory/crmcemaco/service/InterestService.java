package com.softechfactory.crmcemaco.service;

import com.softechfactory.crmcemaco.service.dto.InterestDTO;
import java.util.List;

/**
 * Service Interface for managing Interest.
 */
public interface InterestService {

    /**
     * Save a interest.
     *
     * @param interestDTO the entity to save
     * @return the persisted entity
     */
    InterestDTO save(InterestDTO interestDTO);

    /**
     * Get all the interests.
     *
     * @return the list of entities
     */
    List<InterestDTO> findAll();

    /**
     * Get the "id" interest.
     *
     * @param id the id of the entity
     * @return the entity
     */
    InterestDTO findOne(Long id);

    /**
     * Delete the "id" interest.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
