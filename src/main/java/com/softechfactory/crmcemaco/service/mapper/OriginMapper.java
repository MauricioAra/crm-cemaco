package com.softechfactory.crmcemaco.service.mapper;

import com.softechfactory.crmcemaco.domain.*;
import com.softechfactory.crmcemaco.service.dto.OriginDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Origin and its DTO OriginDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OriginMapper extends EntityMapper<OriginDTO, Origin> {



    default Origin fromId(Long id) {
        if (id == null) {
            return null;
        }
        Origin origin = new Origin();
        origin.setId(id);
        return origin;
    }
}
