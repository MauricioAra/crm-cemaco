package com.softechfactory.crmcemaco.service.mapper;

import com.softechfactory.crmcemaco.domain.*;
import com.softechfactory.crmcemaco.service.dto.FollowDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Follow and its DTO FollowDTO.
 */
@Mapper(componentModel = "spring", uses = {ContactMapper.class})
public interface FollowMapper extends EntityMapper<FollowDTO, Follow> {

    @Mapping(source = "contact.id", target = "contactId")
    @Mapping(source = "contact.name", target = "contactName")
    FollowDTO toDto(Follow follow);

    @Mapping(source = "contactId", target = "contact")
    Follow toEntity(FollowDTO followDTO);

    default Follow fromId(Long id) {
        if (id == null) {
            return null;
        }
        Follow follow = new Follow();
        follow.setId(id);
        return follow;
    }
}
