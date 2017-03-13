package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Companydetail;
import com.hartron.investharyana.repository.CompanydetailRepository;
import com.hartron.investharyana.service.CompanydetailService;
import com.hartron.investharyana.service.dto.CompanydetailDTO;
import com.hartron.investharyana.service.mapper.CompanydetailMapper;
import com.hartron.investharyana.web.rest.errors.ExceptionTranslator;

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
import org.springframework.util.Base64Utils;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CompanydetailResource REST controller.
 *
 * @see CompanydetailResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class CompanydetailResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_INVESTORID = UUID.randomUUID();
    private static final UUID UPDATED_INVESTORID = UUID.randomUUID();

    private static final String DEFAULT_PROMOTER_MD_DIRECTOR = "AAAAAAAAAA";
    private static final String UPDATED_PROMOTER_MD_DIRECTOR = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESSENTITY = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESSENTITY = "BBBBBBBBBB";

    private static final UUID DEFAULT_BUSINESSENTITYTYPE = UUID.randomUUID();
    private static final UUID UPDATED_BUSINESSENTITYTYPE = UUID.randomUUID();

    private static final Integer DEFAULT_DIRECTOR_PROMOTER_MD_CEO_NUMBER = 1;
    private static final Integer UPDATED_DIRECTOR_PROMOTER_MD_CEO_NUMBER = 2;

    private static final ByteBuffer DEFAULT_DIRECTOR_MD_CEO_LIST = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_DIRECTOR_MD_CEO_LIST = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_DIRECTOR_MD_CEO_LIST_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_DIRECTOR_MD_CEO_LIST_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_PAN_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PAN_NUMBER = "BBBBBBBBBB";

    private static final ByteBuffer DEFAULT_PANCARD = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_PANCARD = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_PANCARD_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PANCARD_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_AADHAR_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_AADHAR_NUMBER = "BBBBBBBBBB";

    private static final ByteBuffer DEFAULT_AADHARCARD = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_AADHARCARD = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_AADHARCARD_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_AADHARCARD_CONTENT_TYPE = "image/png";

    private static final Boolean DEFAULT_NRI = false;
    private static final Boolean UPDATED_NRI = true;

    private static final String DEFAULT_TIN_VAT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TIN_VAT_NUMBER = "BBBBBBBBBB";

    private static final ByteBuffer DEFAULT_TIN_VAT_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_TIN_VAT_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_TIN_VAT_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_TIN_VAT_DOCUMENT_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_CST_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CST_NUMBER = "BBBBBBBBBB";

    private static final ByteBuffer DEFAULT_CST_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_CST_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_CST_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CST_DOCUMENT_CONTENT_TYPE = "image/png";

    private static final ByteBuffer DEFAULT_MOA_PARTNERSHIPDEED = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_MOA_PARTNERSHIPDEED = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_MOA_PARTNERSHIPDEED_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_MOA_PARTNERSHIPDEED_CONTENT_TYPE = "image/png";

    private static final ByteBuffer DEFAULT_REGISTRATION_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_REGISTRATION_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_REGISTRATION_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_REGISTRATION_DOCUMENT_CONTENT_TYPE = "image/png";

    @Autowired
    private CompanydetailRepository companydetailRepository;

    @Autowired
    private CompanydetailMapper companydetailMapper;

    @Autowired
    private CompanydetailService companydetailService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restCompanydetailMockMvc;

    private Companydetail companydetail;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CompanydetailResource companydetailResource = new CompanydetailResource(companydetailService);
        this.restCompanydetailMockMvc = MockMvcBuilders.standaloneSetup(companydetailResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Companydetail createEntity() {
        Companydetail companydetail = new Companydetail()
                .investorid(DEFAULT_INVESTORID)
                .promoter_md_director(DEFAULT_PROMOTER_MD_DIRECTOR)
                .designation(DEFAULT_DESIGNATION)
                .businessentity(DEFAULT_BUSINESSENTITY)
                .businessentitytype(DEFAULT_BUSINESSENTITYTYPE)
                .director_promoter_md_ceo_number(DEFAULT_DIRECTOR_PROMOTER_MD_CEO_NUMBER)
                .director_md_ceo_list(DEFAULT_DIRECTOR_MD_CEO_LIST)
                .director_md_ceo_listContentType(DEFAULT_DIRECTOR_MD_CEO_LIST_CONTENT_TYPE)
                .pan_number(DEFAULT_PAN_NUMBER)
                .pancard(DEFAULT_PANCARD)
                .pancardContentType(DEFAULT_PANCARD_CONTENT_TYPE)
                .aadhar_number(DEFAULT_AADHAR_NUMBER)
                .aadharcard(DEFAULT_AADHARCARD)
                .aadharcardContentType(DEFAULT_AADHARCARD_CONTENT_TYPE)
                .nri(DEFAULT_NRI)
                .tin_vat_number(DEFAULT_TIN_VAT_NUMBER)
                .tin_vat_document(DEFAULT_TIN_VAT_DOCUMENT)
                .tin_vat_documentContentType(DEFAULT_TIN_VAT_DOCUMENT_CONTENT_TYPE)
                .cst_number(DEFAULT_CST_NUMBER)
                .cst_document(DEFAULT_CST_DOCUMENT)
                .cst_documentContentType(DEFAULT_CST_DOCUMENT_CONTENT_TYPE)
                .moa_partnershipdeed(DEFAULT_MOA_PARTNERSHIPDEED)
                .moa_partnershipdeedContentType(DEFAULT_MOA_PARTNERSHIPDEED_CONTENT_TYPE)
                .registration_document(DEFAULT_REGISTRATION_DOCUMENT)
                .registration_documentContentType(DEFAULT_REGISTRATION_DOCUMENT_CONTENT_TYPE);
        return companydetail;
    }

    @Before
    public void initTest() {
        companydetailRepository.deleteAll();
        companydetail = createEntity();
    }

    @Test
    public void createCompanydetail() throws Exception {
        int databaseSizeBeforeCreate = companydetailRepository.findAll().size();

        // Create the Companydetail
        CompanydetailDTO companydetailDTO = companydetailMapper.companydetailToCompanydetailDTO(companydetail);

        restCompanydetailMockMvc.perform(post("/api/companydetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companydetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Companydetail in the database
        List<Companydetail> companydetailList = companydetailRepository.findAll();
        assertThat(companydetailList).hasSize(databaseSizeBeforeCreate + 1);
        Companydetail testCompanydetail = companydetailList.get(companydetailList.size() - 1);
        assertThat(testCompanydetail.getInvestorid()).isEqualTo(DEFAULT_INVESTORID);
        assertThat(testCompanydetail.getPromoter_md_director()).isEqualTo(DEFAULT_PROMOTER_MD_DIRECTOR);
        assertThat(testCompanydetail.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testCompanydetail.getBusinessentity()).isEqualTo(DEFAULT_BUSINESSENTITY);
        assertThat(testCompanydetail.getBusinessentitytype()).isEqualTo(DEFAULT_BUSINESSENTITYTYPE);
        assertThat(testCompanydetail.getDirector_promoter_md_ceo_number()).isEqualTo(DEFAULT_DIRECTOR_PROMOTER_MD_CEO_NUMBER);
        assertThat(testCompanydetail.getDirector_md_ceo_list()).isEqualTo(DEFAULT_DIRECTOR_MD_CEO_LIST);
        assertThat(testCompanydetail.getDirector_md_ceo_listContentType()).isEqualTo(DEFAULT_DIRECTOR_MD_CEO_LIST_CONTENT_TYPE);
        assertThat(testCompanydetail.getPan_number()).isEqualTo(DEFAULT_PAN_NUMBER);
        assertThat(testCompanydetail.getPancard()).isEqualTo(DEFAULT_PANCARD);
        assertThat(testCompanydetail.getPancardContentType()).isEqualTo(DEFAULT_PANCARD_CONTENT_TYPE);
        assertThat(testCompanydetail.getAadhar_number()).isEqualTo(DEFAULT_AADHAR_NUMBER);
        assertThat(testCompanydetail.getAadharcard()).isEqualTo(DEFAULT_AADHARCARD);
        assertThat(testCompanydetail.getAadharcardContentType()).isEqualTo(DEFAULT_AADHARCARD_CONTENT_TYPE);
        assertThat(testCompanydetail.isNri()).isEqualTo(DEFAULT_NRI);
        assertThat(testCompanydetail.getTin_vat_number()).isEqualTo(DEFAULT_TIN_VAT_NUMBER);
        assertThat(testCompanydetail.getTin_vat_document()).isEqualTo(DEFAULT_TIN_VAT_DOCUMENT);
        assertThat(testCompanydetail.getTin_vat_documentContentType()).isEqualTo(DEFAULT_TIN_VAT_DOCUMENT_CONTENT_TYPE);
        assertThat(testCompanydetail.getCst_number()).isEqualTo(DEFAULT_CST_NUMBER);
        assertThat(testCompanydetail.getCst_document()).isEqualTo(DEFAULT_CST_DOCUMENT);
        assertThat(testCompanydetail.getCst_documentContentType()).isEqualTo(DEFAULT_CST_DOCUMENT_CONTENT_TYPE);
        assertThat(testCompanydetail.getMoa_partnershipdeed()).isEqualTo(DEFAULT_MOA_PARTNERSHIPDEED);
        assertThat(testCompanydetail.getMoa_partnershipdeedContentType()).isEqualTo(DEFAULT_MOA_PARTNERSHIPDEED_CONTENT_TYPE);
        assertThat(testCompanydetail.getRegistration_document()).isEqualTo(DEFAULT_REGISTRATION_DOCUMENT);
        assertThat(testCompanydetail.getRegistration_documentContentType()).isEqualTo(DEFAULT_REGISTRATION_DOCUMENT_CONTENT_TYPE);
    }

    @Test
    public void createCompanydetailWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = companydetailRepository.findAll().size();

        // Create the Companydetail with an existing ID
        Companydetail existingCompanydetail = new Companydetail();
        existingCompanydetail.setId(UUID.randomUUID());
        CompanydetailDTO existingCompanydetailDTO = companydetailMapper.companydetailToCompanydetailDTO(existingCompanydetail);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompanydetailMockMvc.perform(post("/api/companydetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingCompanydetailDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Companydetail> companydetailList = companydetailRepository.findAll();
        assertThat(companydetailList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllCompanydetails() throws Exception {
        // Initialize the database
        companydetailRepository.save(companydetail);

        // Get all the companydetailList
        restCompanydetailMockMvc.perform(get("/api/companydetails?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(companydetail.getId().toString())))
            .andExpect(jsonPath("$.[*].investorid").value(hasItem(DEFAULT_INVESTORID.toString())))
            .andExpect(jsonPath("$.[*].promoter_md_director").value(hasItem(DEFAULT_PROMOTER_MD_DIRECTOR.toString())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())))
            .andExpect(jsonPath("$.[*].businessentity").value(hasItem(DEFAULT_BUSINESSENTITY.toString())))
            .andExpect(jsonPath("$.[*].businessentitytype").value(hasItem(DEFAULT_BUSINESSENTITYTYPE.toString())))
            .andExpect(jsonPath("$.[*].director_promoter_md_ceo_number").value(hasItem(DEFAULT_DIRECTOR_PROMOTER_MD_CEO_NUMBER)))
            .andExpect(jsonPath("$.[*].director_md_ceo_listContentType").value(hasItem(DEFAULT_DIRECTOR_MD_CEO_LIST_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].director_md_ceo_list").value(hasItem(Base64Utils.encodeToString(DEFAULT_DIRECTOR_MD_CEO_LIST.array()))))
            .andExpect(jsonPath("$.[*].pan_number").value(hasItem(DEFAULT_PAN_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].pancardContentType").value(hasItem(DEFAULT_PANCARD_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].pancard").value(hasItem(Base64Utils.encodeToString(DEFAULT_PANCARD.array()))))
            .andExpect(jsonPath("$.[*].aadhar_number").value(hasItem(DEFAULT_AADHAR_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].aadharcardContentType").value(hasItem(DEFAULT_AADHARCARD_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].aadharcard").value(hasItem(Base64Utils.encodeToString(DEFAULT_AADHARCARD.array()))))
            .andExpect(jsonPath("$.[*].nri").value(hasItem(DEFAULT_NRI.booleanValue())))
            .andExpect(jsonPath("$.[*].tin_vat_number").value(hasItem(DEFAULT_TIN_VAT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].tin_vat_documentContentType").value(hasItem(DEFAULT_TIN_VAT_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].tin_vat_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_TIN_VAT_DOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].cst_number").value(hasItem(DEFAULT_CST_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].cst_documentContentType").value(hasItem(DEFAULT_CST_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].cst_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_CST_DOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].moa_partnershipdeedContentType").value(hasItem(DEFAULT_MOA_PARTNERSHIPDEED_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].moa_partnershipdeed").value(hasItem(Base64Utils.encodeToString(DEFAULT_MOA_PARTNERSHIPDEED.array()))))
            .andExpect(jsonPath("$.[*].registration_documentContentType").value(hasItem(DEFAULT_REGISTRATION_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].registration_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_REGISTRATION_DOCUMENT.array()))));
    }

    @Test
    public void getCompanydetail() throws Exception {
        // Initialize the database
        companydetailRepository.save(companydetail);

        // Get the companydetail
        restCompanydetailMockMvc.perform(get("/api/companydetails/{id}", companydetail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(companydetail.getId().toString()))
            .andExpect(jsonPath("$.investorid").value(DEFAULT_INVESTORID.toString()))
            .andExpect(jsonPath("$.promoter_md_director").value(DEFAULT_PROMOTER_MD_DIRECTOR.toString()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()))
            .andExpect(jsonPath("$.businessentity").value(DEFAULT_BUSINESSENTITY.toString()))
            .andExpect(jsonPath("$.businessentitytype").value(DEFAULT_BUSINESSENTITYTYPE.toString()))
            .andExpect(jsonPath("$.director_promoter_md_ceo_number").value(DEFAULT_DIRECTOR_PROMOTER_MD_CEO_NUMBER))
            .andExpect(jsonPath("$.director_md_ceo_listContentType").value(DEFAULT_DIRECTOR_MD_CEO_LIST_CONTENT_TYPE))
            .andExpect(jsonPath("$.director_md_ceo_list").value(Base64Utils.encodeToString(DEFAULT_DIRECTOR_MD_CEO_LIST.array())))
            .andExpect(jsonPath("$.pan_number").value(DEFAULT_PAN_NUMBER.toString()))
            .andExpect(jsonPath("$.pancardContentType").value(DEFAULT_PANCARD_CONTENT_TYPE))
            .andExpect(jsonPath("$.pancard").value(Base64Utils.encodeToString(DEFAULT_PANCARD.array())))
            .andExpect(jsonPath("$.aadhar_number").value(DEFAULT_AADHAR_NUMBER.toString()))
            .andExpect(jsonPath("$.aadharcardContentType").value(DEFAULT_AADHARCARD_CONTENT_TYPE))
            .andExpect(jsonPath("$.aadharcard").value(Base64Utils.encodeToString(DEFAULT_AADHARCARD.array())))
            .andExpect(jsonPath("$.nri").value(DEFAULT_NRI.booleanValue()))
            .andExpect(jsonPath("$.tin_vat_number").value(DEFAULT_TIN_VAT_NUMBER.toString()))
            .andExpect(jsonPath("$.tin_vat_documentContentType").value(DEFAULT_TIN_VAT_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.tin_vat_document").value(Base64Utils.encodeToString(DEFAULT_TIN_VAT_DOCUMENT.array())))
            .andExpect(jsonPath("$.cst_number").value(DEFAULT_CST_NUMBER.toString()))
            .andExpect(jsonPath("$.cst_documentContentType").value(DEFAULT_CST_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.cst_document").value(Base64Utils.encodeToString(DEFAULT_CST_DOCUMENT.array())))
            .andExpect(jsonPath("$.moa_partnershipdeedContentType").value(DEFAULT_MOA_PARTNERSHIPDEED_CONTENT_TYPE))
            .andExpect(jsonPath("$.moa_partnershipdeed").value(Base64Utils.encodeToString(DEFAULT_MOA_PARTNERSHIPDEED.array())))
            .andExpect(jsonPath("$.registration_documentContentType").value(DEFAULT_REGISTRATION_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.registration_document").value(Base64Utils.encodeToString(DEFAULT_REGISTRATION_DOCUMENT.array())));
    }

    @Test
    public void getNonExistingCompanydetail() throws Exception {
        // Get the companydetail
        restCompanydetailMockMvc.perform(get("/api/companydetails/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCompanydetail() throws Exception {
        // Initialize the database
        companydetailRepository.save(companydetail);
        int databaseSizeBeforeUpdate = companydetailRepository.findAll().size();

        // Update the companydetail
        Companydetail updatedCompanydetail = companydetailRepository.findOne(companydetail.getId());
        updatedCompanydetail
                .investorid(UPDATED_INVESTORID)
                .promoter_md_director(UPDATED_PROMOTER_MD_DIRECTOR)
                .designation(UPDATED_DESIGNATION)
                .businessentity(UPDATED_BUSINESSENTITY)
                .businessentitytype(UPDATED_BUSINESSENTITYTYPE)
                .director_promoter_md_ceo_number(UPDATED_DIRECTOR_PROMOTER_MD_CEO_NUMBER)
                .director_md_ceo_list(UPDATED_DIRECTOR_MD_CEO_LIST)
                .director_md_ceo_listContentType(UPDATED_DIRECTOR_MD_CEO_LIST_CONTENT_TYPE)
                .pan_number(UPDATED_PAN_NUMBER)
                .pancard(UPDATED_PANCARD)
                .pancardContentType(UPDATED_PANCARD_CONTENT_TYPE)
                .aadhar_number(UPDATED_AADHAR_NUMBER)
                .aadharcard(UPDATED_AADHARCARD)
                .aadharcardContentType(UPDATED_AADHARCARD_CONTENT_TYPE)
                .nri(UPDATED_NRI)
                .tin_vat_number(UPDATED_TIN_VAT_NUMBER)
                .tin_vat_document(UPDATED_TIN_VAT_DOCUMENT)
                .tin_vat_documentContentType(UPDATED_TIN_VAT_DOCUMENT_CONTENT_TYPE)
                .cst_number(UPDATED_CST_NUMBER)
                .cst_document(UPDATED_CST_DOCUMENT)
                .cst_documentContentType(UPDATED_CST_DOCUMENT_CONTENT_TYPE)
                .moa_partnershipdeed(UPDATED_MOA_PARTNERSHIPDEED)
                .moa_partnershipdeedContentType(UPDATED_MOA_PARTNERSHIPDEED_CONTENT_TYPE)
                .registration_document(UPDATED_REGISTRATION_DOCUMENT)
                .registration_documentContentType(UPDATED_REGISTRATION_DOCUMENT_CONTENT_TYPE);
        CompanydetailDTO companydetailDTO = companydetailMapper.companydetailToCompanydetailDTO(updatedCompanydetail);

        restCompanydetailMockMvc.perform(put("/api/companydetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companydetailDTO)))
            .andExpect(status().isOk());

        // Validate the Companydetail in the database
        List<Companydetail> companydetailList = companydetailRepository.findAll();
        assertThat(companydetailList).hasSize(databaseSizeBeforeUpdate);
        Companydetail testCompanydetail = companydetailList.get(companydetailList.size() - 1);
        assertThat(testCompanydetail.getInvestorid()).isEqualTo(UPDATED_INVESTORID);
        assertThat(testCompanydetail.getPromoter_md_director()).isEqualTo(UPDATED_PROMOTER_MD_DIRECTOR);
        assertThat(testCompanydetail.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testCompanydetail.getBusinessentity()).isEqualTo(UPDATED_BUSINESSENTITY);
        assertThat(testCompanydetail.getBusinessentitytype()).isEqualTo(UPDATED_BUSINESSENTITYTYPE);
        assertThat(testCompanydetail.getDirector_promoter_md_ceo_number()).isEqualTo(UPDATED_DIRECTOR_PROMOTER_MD_CEO_NUMBER);
        assertThat(testCompanydetail.getDirector_md_ceo_list()).isEqualTo(UPDATED_DIRECTOR_MD_CEO_LIST);
        assertThat(testCompanydetail.getDirector_md_ceo_listContentType()).isEqualTo(UPDATED_DIRECTOR_MD_CEO_LIST_CONTENT_TYPE);
        assertThat(testCompanydetail.getPan_number()).isEqualTo(UPDATED_PAN_NUMBER);
        assertThat(testCompanydetail.getPancard()).isEqualTo(UPDATED_PANCARD);
        assertThat(testCompanydetail.getPancardContentType()).isEqualTo(UPDATED_PANCARD_CONTENT_TYPE);
        assertThat(testCompanydetail.getAadhar_number()).isEqualTo(UPDATED_AADHAR_NUMBER);
        assertThat(testCompanydetail.getAadharcard()).isEqualTo(UPDATED_AADHARCARD);
        assertThat(testCompanydetail.getAadharcardContentType()).isEqualTo(UPDATED_AADHARCARD_CONTENT_TYPE);
        assertThat(testCompanydetail.isNri()).isEqualTo(UPDATED_NRI);
        assertThat(testCompanydetail.getTin_vat_number()).isEqualTo(UPDATED_TIN_VAT_NUMBER);
        assertThat(testCompanydetail.getTin_vat_document()).isEqualTo(UPDATED_TIN_VAT_DOCUMENT);
        assertThat(testCompanydetail.getTin_vat_documentContentType()).isEqualTo(UPDATED_TIN_VAT_DOCUMENT_CONTENT_TYPE);
        assertThat(testCompanydetail.getCst_number()).isEqualTo(UPDATED_CST_NUMBER);
        assertThat(testCompanydetail.getCst_document()).isEqualTo(UPDATED_CST_DOCUMENT);
        assertThat(testCompanydetail.getCst_documentContentType()).isEqualTo(UPDATED_CST_DOCUMENT_CONTENT_TYPE);
        assertThat(testCompanydetail.getMoa_partnershipdeed()).isEqualTo(UPDATED_MOA_PARTNERSHIPDEED);
        assertThat(testCompanydetail.getMoa_partnershipdeedContentType()).isEqualTo(UPDATED_MOA_PARTNERSHIPDEED_CONTENT_TYPE);
        assertThat(testCompanydetail.getRegistration_document()).isEqualTo(UPDATED_REGISTRATION_DOCUMENT);
        assertThat(testCompanydetail.getRegistration_documentContentType()).isEqualTo(UPDATED_REGISTRATION_DOCUMENT_CONTENT_TYPE);
    }

    @Test
    public void updateNonExistingCompanydetail() throws Exception {
        int databaseSizeBeforeUpdate = companydetailRepository.findAll().size();

        // Create the Companydetail
        CompanydetailDTO companydetailDTO = companydetailMapper.companydetailToCompanydetailDTO(companydetail);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCompanydetailMockMvc.perform(put("/api/companydetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companydetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Companydetail in the database
        List<Companydetail> companydetailList = companydetailRepository.findAll();
        assertThat(companydetailList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteCompanydetail() throws Exception {
        // Initialize the database
        companydetailRepository.save(companydetail);
        int databaseSizeBeforeDelete = companydetailRepository.findAll().size();

        // Get the companydetail
        restCompanydetailMockMvc.perform(delete("/api/companydetails/{id}", companydetail.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Companydetail> companydetailList = companydetailRepository.findAll();
        assertThat(companydetailList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Companydetail.class);
    }
}
