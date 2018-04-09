package com.softechfactory.crmcemaco.service;

import com.softechfactory.crmcemaco.service.dto.FollowDTO;
import java.util.List;

/**
 * Service Interface for managing Follow.
 */
public interface FollowService {

    /**
     * Save a follow.
     *
     * @param followDTO the entity to save
     * @return the persisted entity
     */
    FollowDTO save(FollowDTO followDTO);

    /**
     * Get all the follows.
     *
     * @return the list of entities
     */
    List<FollowDTO> findAll();

    /**
     * Get the "id" follow.
     *
     * @param id the id of the entity
     * @return the entity
     */
    FollowDTO findOne(Long id);

    /**
     * Delete the "id" follow.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    List<FollowDTO> findFollowByNextContact(String date);

    List<FollowDTO> findFollowByRegistryDate(String date);

    List<FollowDTO> findFollowByContact(Long id);
}
