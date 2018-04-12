package com.softechfactory.crmcemaco.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.softechfactory.crmcemaco.service.InterestService;
import com.softechfactory.crmcemaco.web.rest.errors.BadRequestAlertException;
import com.softechfactory.crmcemaco.web.rest.util.HeaderUtil;
import com.softechfactory.crmcemaco.service.dto.InterestDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Interest.
 */
@RestController
@RequestMapping("/api")
public class InterestResource {

    private final Logger log = LoggerFactory.getLogger(InterestResource.class);

    private static final String ENTITY_NAME = "interest";

    private final InterestService interestService;

    public InterestResource(InterestService interestService) {
        this.interestService = interestService;
    }

    /**
     * POST  /interests : Create a new interest.
     *
     * @param interestDTO the interestDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new interestDTO, or with status 400 (Bad Request) if the interest has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/interests")
    @Timed
    public ResponseEntity<InterestDTO> createInterest(@RequestBody InterestDTO interestDTO) throws URISyntaxException {
        log.debug("REST request to save Interest : {}", interestDTO);
        if (interestDTO.getId() != null) {
            throw new BadRequestAlertException("A new interest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InterestDTO result = interestService.save(interestDTO);
        return ResponseEntity.created(new URI("/api/interests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /interests : Updates an existing interest.
     *
     * @param interestDTO the interestDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated interestDTO,
     * or with status 400 (Bad Request) if the interestDTO is not valid,
     * or with status 500 (Internal Server Error) if the interestDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/interests")
    @Timed
    public ResponseEntity<InterestDTO> updateInterest(@RequestBody InterestDTO interestDTO) throws URISyntaxException {
        log.debug("REST request to update Interest : {}", interestDTO);
        if (interestDTO.getId() == null) {
            return createInterest(interestDTO);
        }
        InterestDTO result = interestService.save(interestDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, interestDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /interests : get all the interests.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of interests in body
     */
    @GetMapping("/interests")
    @Timed
    public List<InterestDTO> getAllInterests() {
        log.debug("REST request to get all Interests");
        return interestService.findAll();
        }

    /**
     * GET  /interests/:id : get the "id" interest.
     *
     * @param id the id of the interestDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the interestDTO, or with status 404 (Not Found)
     */
    @GetMapping("/interests/{id}")
    @Timed
    public ResponseEntity<InterestDTO> getInterest(@PathVariable Long id) {
        log.debug("REST request to get Interest : {}", id);
        InterestDTO interestDTO = interestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(interestDTO));
    }

    /**
     * DELETE  /interests/:id : delete the "id" interest.
     *
     * @param id the id of the interestDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/interests/{id}")
    @Timed
    public ResponseEntity<Void> deleteInterest(@PathVariable Long id) {
        log.debug("REST request to delete Interest : {}", id);
        interestService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
