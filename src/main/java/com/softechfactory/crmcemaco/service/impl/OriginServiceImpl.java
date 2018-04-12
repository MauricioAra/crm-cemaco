package com.softechfactory.crmcemaco.service.impl;

import com.softechfactory.crmcemaco.service.OriginService;
import com.softechfactory.crmcemaco.domain.Origin;
import com.softechfactory.crmcemaco.repository.OriginRepository;
import com.softechfactory.crmcemaco.service.dto.OriginDTO;
import com.softechfactory.crmcemaco.service.mapper.OriginMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Origin.
 */
@Service
@Transactional
public class OriginServiceImpl implements OriginService {

    private final Logger log = LoggerFactory.getLogger(OriginServiceImpl.class);

    private final OriginRepository originRepository;

    private final OriginMapper originMapper;

    public OriginServiceImpl(OriginRepository originRepository, OriginMapper originMapper) {
        this.originRepository = originRepository;
        this.originMapper = originMapper;
    }

    /**
     * Save a origin.
     *
     * @param originDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OriginDTO save(OriginDTO originDTO) {
        log.debug("Request to save Origin : {}", originDTO);
        Origin origin = originMapper.toEntity(originDTO);
        origin = originRepository.save(origin);
        return originMapper.toDto(origin);
    }

    /**
     * Get all the origins.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OriginDTO> findAll() {
        log.debug("Request to get all Origins");
        return originRepository.findAll().stream()
            .map(originMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one origin by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OriginDTO findOne(Long id) {
        log.debug("Request to get Origin : {}", id);
        Origin origin = originRepository.findOne(id);
        return originMapper.toDto(origin);
    }

    /**
     * Delete the origin by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Origin : {}", id);
        originRepository.delete(id);
    }
}
