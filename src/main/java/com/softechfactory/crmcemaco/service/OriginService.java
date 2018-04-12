package com.softechfactory.crmcemaco.service;

import com.softechfactory.crmcemaco.service.dto.OriginDTO;
import java.util.List;

/**
 * Service Interface for managing Origin.
 */
public interface OriginService {

    /**
     * Save a origin.
     *
     * @param originDTO the entity to save
     * @return the persisted entity
     */
    OriginDTO save(OriginDTO originDTO);

    /**
     * Get all the origins.
     *
     * @return the list of entities
     */
    List<OriginDTO> findAll();

    /**
     * Get the "id" origin.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OriginDTO findOne(Long id);

    /**
     * Delete the "id" origin.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
