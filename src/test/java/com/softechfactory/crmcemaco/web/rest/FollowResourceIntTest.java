package com.softechfactory.crmcemaco.web.rest;

import com.softechfactory.crmcemaco.CrmcemacoApp;

import com.softechfactory.crmcemaco.domain.Follow;
import com.softechfactory.crmcemaco.repository.FollowRepository;
import com.softechfactory.crmcemaco.service.FollowService;
import com.softechfactory.crmcemaco.service.dto.FollowDTO;
import com.softechfactory.crmcemaco.service.mapper.FollowMapper;
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
 * Test class for the FollowResource REST controller.
 *
 * @see FollowResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrmcemacoApp.class)
public class FollowResourceIntTest {

    private static final String DEFAULT_ORIGIN_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_ORIGIN_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_ORIGIN_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_SUB_ORIGIN_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_RESULT = "AAAAAAAAAA";
    private static final String UPDATED_RESULT = "BBBBBBBBBB";

    private static final String DEFAULT_NEXT_CONTACT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_NEXT_CONTACT_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_REGISTRY_DATE = "AAAAAAAAAA";
    private static final String UPDATED_REGISTRY_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_NEXT_CONTACT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_NEXT_CONTACT_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_FAVORITE_CARD = "AAAAAAAAAA";
    private static final String UPDATED_FAVORITE_CARD = "BBBBBBBBBB";

    private static final String DEFAULT_BUY_IN_CEMACO = "AAAAAAAAAA";
    private static final String UPDATED_BUY_IN_CEMACO = "BBBBBBBBBB";

    private static final String DEFAULT_INTERESTED_BUY = "AAAAAAAAAA";
    private static final String UPDATED_INTERESTED_BUY = "BBBBBBBBBB";

    private static final String DEFAULT_ARTICLE = "AAAAAAAAAA";
    private static final String UPDATED_ARTICLE = "BBBBBBBBBB";

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private FollowMapper followMapper;

    @Autowired
    private FollowService followService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFollowMockMvc;

    private Follow follow;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FollowResource followResource = new FollowResource(followService);
        this.restFollowMockMvc = MockMvcBuilders.standaloneSetup(followResource)
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
    public static Follow createEntity(EntityManager em) {
        Follow follow = new Follow()
            .originContact(DEFAULT_ORIGIN_CONTACT)
            .subOriginContact(DEFAULT_SUB_ORIGIN_CONTACT)
            .result(DEFAULT_RESULT)
            .nextContactDate(DEFAULT_NEXT_CONTACT_DATE)
            .registryDate(DEFAULT_REGISTRY_DATE)
            .status(DEFAULT_STATUS)
            .nextContactReason(DEFAULT_NEXT_CONTACT_REASON)
            .favoriteCard(DEFAULT_FAVORITE_CARD)
            .buyInCemaco(DEFAULT_BUY_IN_CEMACO)
            .interestedBuy(DEFAULT_INTERESTED_BUY)
            .article(DEFAULT_ARTICLE);
        return follow;
    }

    @Before
    public void initTest() {
        follow = createEntity(em);
    }

    @Test
    @Transactional
    public void createFollow() throws Exception {
        int databaseSizeBeforeCreate = followRepository.findAll().size();

        // Create the Follow
        FollowDTO followDTO = followMapper.toDto(follow);
        restFollowMockMvc.perform(post("/api/follows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(followDTO)))
            .andExpect(status().isCreated());

        // Validate the Follow in the database
        List<Follow> followList = followRepository.findAll();
        assertThat(followList).hasSize(databaseSizeBeforeCreate + 1);
        Follow testFollow = followList.get(followList.size() - 1);
        assertThat(testFollow.getOriginContact()).isEqualTo(DEFAULT_ORIGIN_CONTACT);
        assertThat(testFollow.getSubOriginContact()).isEqualTo(DEFAULT_SUB_ORIGIN_CONTACT);
        assertThat(testFollow.getResult()).isEqualTo(DEFAULT_RESULT);
        assertThat(testFollow.getNextContactDate()).isEqualTo(DEFAULT_NEXT_CONTACT_DATE);
        assertThat(testFollow.getRegistryDate()).isEqualTo(DEFAULT_REGISTRY_DATE);
        assertThat(testFollow.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFollow.getNextContactReason()).isEqualTo(DEFAULT_NEXT_CONTACT_REASON);
        assertThat(testFollow.getFavoriteCard()).isEqualTo(DEFAULT_FAVORITE_CARD);
        assertThat(testFollow.getBuyInCemaco()).isEqualTo(DEFAULT_BUY_IN_CEMACO);
        assertThat(testFollow.getInterestedBuy()).isEqualTo(DEFAULT_INTERESTED_BUY);
        assertThat(testFollow.getArticle()).isEqualTo(DEFAULT_ARTICLE);
    }

    @Test
    @Transactional
    public void createFollowWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = followRepository.findAll().size();

        // Create the Follow with an existing ID
        follow.setId(1L);
        FollowDTO followDTO = followMapper.toDto(follow);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFollowMockMvc.perform(post("/api/follows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(followDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Follow in the database
        List<Follow> followList = followRepository.findAll();
        assertThat(followList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFollows() throws Exception {
        // Initialize the database
        followRepository.saveAndFlush(follow);

        // Get all the followList
        restFollowMockMvc.perform(get("/api/follows?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(follow.getId().intValue())))
            .andExpect(jsonPath("$.[*].originContact").value(hasItem(DEFAULT_ORIGIN_CONTACT.toString())))
            .andExpect(jsonPath("$.[*].subOriginContact").value(hasItem(DEFAULT_SUB_ORIGIN_CONTACT.toString())))
            .andExpect(jsonPath("$.[*].result").value(hasItem(DEFAULT_RESULT.toString())))
            .andExpect(jsonPath("$.[*].nextContactDate").value(hasItem(DEFAULT_NEXT_CONTACT_DATE.toString())))
            .andExpect(jsonPath("$.[*].registryDate").value(hasItem(DEFAULT_REGISTRY_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].nextContactReason").value(hasItem(DEFAULT_NEXT_CONTACT_REASON.toString())))
            .andExpect(jsonPath("$.[*].favoriteCard").value(hasItem(DEFAULT_FAVORITE_CARD.toString())))
            .andExpect(jsonPath("$.[*].buyInCemaco").value(hasItem(DEFAULT_BUY_IN_CEMACO.toString())))
            .andExpect(jsonPath("$.[*].interestedBuy").value(hasItem(DEFAULT_INTERESTED_BUY.toString())))
            .andExpect(jsonPath("$.[*].article").value(hasItem(DEFAULT_ARTICLE.toString())));
    }

    @Test
    @Transactional
    public void getFollow() throws Exception {
        // Initialize the database
        followRepository.saveAndFlush(follow);

        // Get the follow
        restFollowMockMvc.perform(get("/api/follows/{id}", follow.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(follow.getId().intValue()))
            .andExpect(jsonPath("$.originContact").value(DEFAULT_ORIGIN_CONTACT.toString()))
            .andExpect(jsonPath("$.subOriginContact").value(DEFAULT_SUB_ORIGIN_CONTACT.toString()))
            .andExpect(jsonPath("$.result").value(DEFAULT_RESULT.toString()))
            .andExpect(jsonPath("$.nextContactDate").value(DEFAULT_NEXT_CONTACT_DATE.toString()))
            .andExpect(jsonPath("$.registryDate").value(DEFAULT_REGISTRY_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.nextContactReason").value(DEFAULT_NEXT_CONTACT_REASON.toString()))
            .andExpect(jsonPath("$.favoriteCard").value(DEFAULT_FAVORITE_CARD.toString()))
            .andExpect(jsonPath("$.buyInCemaco").value(DEFAULT_BUY_IN_CEMACO.toString()))
            .andExpect(jsonPath("$.interestedBuy").value(DEFAULT_INTERESTED_BUY.toString()))
            .andExpect(jsonPath("$.article").value(DEFAULT_ARTICLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFollow() throws Exception {
        // Get the follow
        restFollowMockMvc.perform(get("/api/follows/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFollow() throws Exception {
        // Initialize the database
        followRepository.saveAndFlush(follow);
        int databaseSizeBeforeUpdate = followRepository.findAll().size();

        // Update the follow
        Follow updatedFollow = followRepository.findOne(follow.getId());
        // Disconnect from session so that the updates on updatedFollow are not directly saved in db
        em.detach(updatedFollow);
        updatedFollow
            .originContact(UPDATED_ORIGIN_CONTACT)
            .subOriginContact(UPDATED_SUB_ORIGIN_CONTACT)
            .result(UPDATED_RESULT)
            .nextContactDate(UPDATED_NEXT_CONTACT_DATE)
            .registryDate(UPDATED_REGISTRY_DATE)
            .status(UPDATED_STATUS)
            .nextContactReason(UPDATED_NEXT_CONTACT_REASON)
            .favoriteCard(UPDATED_FAVORITE_CARD)
            .buyInCemaco(UPDATED_BUY_IN_CEMACO)
            .interestedBuy(UPDATED_INTERESTED_BUY)
            .article(UPDATED_ARTICLE);
        FollowDTO followDTO = followMapper.toDto(updatedFollow);

        restFollowMockMvc.perform(put("/api/follows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(followDTO)))
            .andExpect(status().isOk());

        // Validate the Follow in the database
        List<Follow> followList = followRepository.findAll();
        assertThat(followList).hasSize(databaseSizeBeforeUpdate);
        Follow testFollow = followList.get(followList.size() - 1);
        assertThat(testFollow.getOriginContact()).isEqualTo(UPDATED_ORIGIN_CONTACT);
        assertThat(testFollow.getSubOriginContact()).isEqualTo(UPDATED_SUB_ORIGIN_CONTACT);
        assertThat(testFollow.getResult()).isEqualTo(UPDATED_RESULT);
        assertThat(testFollow.getNextContactDate()).isEqualTo(UPDATED_NEXT_CONTACT_DATE);
        assertThat(testFollow.getRegistryDate()).isEqualTo(UPDATED_REGISTRY_DATE);
        assertThat(testFollow.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFollow.getNextContactReason()).isEqualTo(UPDATED_NEXT_CONTACT_REASON);
        assertThat(testFollow.getFavoriteCard()).isEqualTo(UPDATED_FAVORITE_CARD);
        assertThat(testFollow.getBuyInCemaco()).isEqualTo(UPDATED_BUY_IN_CEMACO);
        assertThat(testFollow.getInterestedBuy()).isEqualTo(UPDATED_INTERESTED_BUY);
        assertThat(testFollow.getArticle()).isEqualTo(UPDATED_ARTICLE);
    }

    @Test
    @Transactional
    public void updateNonExistingFollow() throws Exception {
        int databaseSizeBeforeUpdate = followRepository.findAll().size();

        // Create the Follow
        FollowDTO followDTO = followMapper.toDto(follow);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFollowMockMvc.perform(put("/api/follows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(followDTO)))
            .andExpect(status().isCreated());

        // Validate the Follow in the database
        List<Follow> followList = followRepository.findAll();
        assertThat(followList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFollow() throws Exception {
        // Initialize the database
        followRepository.saveAndFlush(follow);
        int databaseSizeBeforeDelete = followRepository.findAll().size();

        // Get the follow
        restFollowMockMvc.perform(delete("/api/follows/{id}", follow.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Follow> followList = followRepository.findAll();
        assertThat(followList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Follow.class);
        Follow follow1 = new Follow();
        follow1.setId(1L);
        Follow follow2 = new Follow();
        follow2.setId(follow1.getId());
        assertThat(follow1).isEqualTo(follow2);
        follow2.setId(2L);
        assertThat(follow1).isNotEqualTo(follow2);
        follow1.setId(null);
        assertThat(follow1).isNotEqualTo(follow2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FollowDTO.class);
        FollowDTO followDTO1 = new FollowDTO();
        followDTO1.setId(1L);
        FollowDTO followDTO2 = new FollowDTO();
        assertThat(followDTO1).isNotEqualTo(followDTO2);
        followDTO2.setId(followDTO1.getId());
        assertThat(followDTO1).isEqualTo(followDTO2);
        followDTO2.setId(2L);
        assertThat(followDTO1).isNotEqualTo(followDTO2);
        followDTO1.setId(null);
        assertThat(followDTO1).isNotEqualTo(followDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(followMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(followMapper.fromId(null)).isNull();
    }
}
