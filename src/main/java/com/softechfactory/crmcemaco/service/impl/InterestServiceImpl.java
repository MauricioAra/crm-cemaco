package com.softechfactory.crmcemaco.service.impl;

import com.softechfactory.crmcemaco.service.InterestService;
import com.softechfactory.crmcemaco.domain.Interest;
import com.softechfactory.crmcemaco.repository.InterestRepository;
import com.softechfactory.crmcemaco.service.dto.InterestDTO;
import com.softechfactory.crmcemaco.service.mapper.InterestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Interest.
 */
@Service
@Transactional
public class InterestServiceImpl implements InterestService {

    private final Logger log = LoggerFactory.getLogger(InterestServiceImpl.class);

    private final InterestRepository interestRepository;

    private final InterestMapper interestMapper;

    public InterestServiceImpl(InterestRepository interestRepository, InterestMapper interestMapper) {
        this.interestRepository = interestRepository;
        this.interestMapper = interestMapper;
    }

    /**
     * Save a interest.
     *
     * @param interestDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public InterestDTO save(InterestDTO interestDTO) {
        log.debug("Request to save Interest : {}", interestDTO);
        Interest interest = interestMapper.toEntity(interestDTO);
        interest = interestRepository.save(interest);
        return interestMapper.toDto(interest);
    }

    /**
     * Get all the interests.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<InterestDTO> findAll() {
        log.debug("Request to get all Interests");
        return interestRepository.findAll().stream()
            .map(interestMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one interest by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public InterestDTO findOne(Long id) {
        log.debug("Request to get Interest : {}", id);
        Interest interest = interestRepository.findOne(id);
        return interestMapper.toDto(interest);
    }

    /**
     * Delete the interest by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Interest : {}", id);
        interestRepository.delete(id);
    }
}
