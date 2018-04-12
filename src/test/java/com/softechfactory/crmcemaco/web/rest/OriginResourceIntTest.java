package com.softechfactory.crmcemaco.web.rest;

import com.softechfactory.crmcemaco.CrmcemacoApp;

import com.softechfactory.crmcemaco.domain.Origin;
import com.softechfactory.crmcemaco.repository.OriginRepository;
import com.softechfactory.crmcemaco.service.OriginService;
import com.softechfactory.crmcemaco.service.dto.OriginDTO;
import com.softechfactory.crmcemaco.service.mapper.OriginMapper;
import com.softechfactory.crmcemaco.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.softechfactory.crmcemaco.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the OriginResource REST controller.
 *
 * @see OriginResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrmcemacoApp.class)
public class OriginResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private OriginRepository originRepository;

    @Autowired
    private OriginMapper originMapper;

    @Autowired
    private OriginService originService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOriginMockMvc;

    private Origin origin;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OriginResource originResource = new OriginResource(originService);
        this.restOriginMockMvc = MockMvcBuilders.standaloneSetup(originResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Origin createEntity(EntityManager em) {
        Origin origin = new Origin()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS);
        return origin;
    }

    @Before
    public void initTest() {
        origin = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrigin() throws Exception {
        int databaseSizeBeforeCreate = originRepository.findAll().size();

        // Create the Origin
        OriginDTO originDTO = originMapper.toDto(origin);
        restOriginMockMvc.perform(post("/api/origins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(originDTO)))
            .andExpect(status().isCreated());

        // Validate the Origin in the database
        List<Origin> originList = originRepository.findAll();
        assertThat(originList).hasSize(databaseSizeBeforeCreate + 1);
        Origin testOrigin = originList.get(originList.size() - 1);
        assertThat(testOrigin.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOrigin.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testOrigin.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createOriginWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = originRepository.findAll().size();

        // Create the Origin with an existing ID
        origin.setId(1L);
        OriginDTO originDTO = originMapper.toDto(origin);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOriginMockMvc.perform(post("/api/origins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(originDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Origin in the database
        List<Origin> originList = originRepository.findAll();
        assertThat(originList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOrigins() throws Exception {
        // Initialize the database
        originRepository.saveAndFlush(origin);

        // Get all the originList
        restOriginMockMvc.perform(get("/api/origins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(origin.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getOrigin() throws Exception {
        // Initialize the database
        originRepository.saveAndFlush(origin);

        // Get the origin
        restOriginMockMvc.perform(get("/api/origins/{id}", origin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(origin.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOrigin() throws Exception {
        // Get the origin
        restOriginMockMvc.perform(get("/api/origins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrigin() throws Exception {
        // Initialize the database
        originRepository.saveAndFlush(origin);
        int databaseSizeBeforeUpdate = originRepository.findAll().size();

        // Update the origin
        Origin updatedOrigin = originRepository.findOne(origin.getId());
        // Disconnect from session so that the updates on updatedOrigin are not directly saved in db
        em.detach(updatedOrigin);
        updatedOrigin
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);
        OriginDTO originDTO = originMapper.toDto(updatedOrigin);

        restOriginMockMvc.perform(put("/api/origins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(originDTO)))
            .andExpect(status().isOk());

        // Validate the Origin in the database
        List<Origin> originList = originRepository.findAll();
        assertThat(originList).hasSize(databaseSizeBeforeUpdate);
        Origin testOrigin = originList.get(originList.size() - 1);
        assertThat(testOrigin.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOrigin.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testOrigin.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingOrigin() throws Exception {
        int databaseSizeBeforeUpdate = originRepository.findAll().size();

        // Create the Origin
        OriginDTO originDTO = originMapper.toDto(origin);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOriginMockMvc.perform(put("/api/origins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(originDTO)))
            .andExpect(status().isCreated());

        // Validate the Origin in the database
        List<Origin> originList = originRepository.findAll();
        assertThat(originList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOrigin() throws Exception {
        // Initialize the database
        originRepository.saveAndFlush(origin);
        int databaseSizeBeforeDelete = originRepository.findAll().size();

        // Get the origin
        restOriginMockMvc.perform(delete("/api/origins/{id}", origin.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Origin> originList = originRepository.findAll();
        assertThat(originList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Origin.class);
        Origin origin1 = new Origin();
        origin1.setId(1L);
        Origin origin2 = new Origin();
        origin2.setId(origin1.getId());
        assertThat(origin1).isEqualTo(origin2);
        origin2.setId(2L);
        assertThat(origin1).isNotEqualTo(origin2);
        origin1.setId(null);
        assertThat(origin1).isNotEqualTo(origin2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OriginDTO.class);
        OriginDTO originDTO1 = new OriginDTO();
        originDTO1.setId(1L);
        OriginDTO originDTO2 = new OriginDTO();
        assertThat(originDTO1).isNotEqualTo(originDTO2);
        originDTO2.setId(originDTO1.getId());
        assertThat(originDTO1).isEqualTo(originDTO2);
        originDTO2.setId(2L);
        assertThat(originDTO1).isNotEqualTo(originDTO2);
        originDTO1.setId(null);
        assertThat(originDTO1).isNotEqualTo(originDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(originMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(originMapper.fromId(null)).isNull();
    }
}
