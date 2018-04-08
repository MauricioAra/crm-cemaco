package com.softechfactory.crmcemaco.service.impl;

import com.softechfactory.crmcemaco.service.FollowService;
import com.softechfactory.crmcemaco.domain.Follow;
import com.softechfactory.crmcemaco.repository.FollowRepository;
import com.softechfactory.crmcemaco.service.dto.FollowDTO;
import com.softechfactory.crmcemaco.service.mapper.FollowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Follow.
 */
@Service
@Transactional
public class FollowServiceImpl implements FollowService {

    private final Logger log = LoggerFactory.getLogger(FollowServiceImpl.class);

    private final FollowRepository followRepository;

    private final FollowMapper followMapper;

    public FollowServiceImpl(FollowRepository followRepository, FollowMapper followMapper) {
        this.followRepository = followRepository;
        this.followMapper = followMapper;
    }

    /**
     * Save a follow.
     *
     * @param followDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FollowDTO save(FollowDTO followDTO) {
        log.debug("Request to save Follow : {}", followDTO);
        Follow follow = followMapper.toEntity(followDTO);
        follow = followRepository.save(follow);
        return followMapper.toDto(follow);
    }

    /**
     * Get all the follows.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<FollowDTO> findAll() {
        log.debug("Request to get all Follows");
        return followRepository.findAll().stream()
            .map(followMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one follow by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public FollowDTO findOne(Long id) {
        log.debug("Request to get Follow : {}", id);
        Follow follow = followRepository.findOne(id);
        return followMapper.toDto(follow);
    }

    /**
     * Delete the follow by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Follow : {}", id);
        followRepository.delete(id);
    }

    @Override
    public List<FollowDTO> findFollowByNextContact(String date) {
        return followRepository.findAllByNextContactDate(date).stream()
            .map(followMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<FollowDTO> findFollowByRegistryDate(String date) {
        return followRepository.findAllByRegistryDate(date).stream()
            .map(followMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
