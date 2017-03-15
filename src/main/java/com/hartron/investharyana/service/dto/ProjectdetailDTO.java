package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Projectdetail entity.
 */
public class ProjectdetailDTO implements Serializable {

    private UUID id;

    private UUID investorid;

    private UUID sector;

    private String projectpurpose;

    private UUID size_of_industry;

    private UUID projectype;

    private String niccode;

    private UUID category_of_project;

    private UUID collaboration_with_foreign_country;

    private ByteBuffer detail_project_report;
    private String detail_project_reportContentType;

    private Boolean existing_regulatory_approval;

    private UUID approval_application_form;

    private ByteBuffer approval_document;
    private String approval_documentContentType;

    private ByteBuffer edc_sif_clu_fee_paid_document;
    private String edc_sif_clu_fee_paid_documentContentType;

    private Boolean edc_sif_clu_fee_paid_applicable;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getInvestorid() {
        return investorid;
    }

    public void setInvestorid(UUID investorid) {
        this.investorid = investorid;
    }
    public UUID getSector() {
        return sector;
    }

    public void setSector(UUID sector) {
        this.sector = sector;
    }
    public String getProjectpurpose() {
        return projectpurpose;
    }

    public void setProjectpurpose(String projectpurpose) {
        this.projectpurpose = projectpurpose;
    }
    public UUID getSize_of_industry() {
        return size_of_industry;
    }

    public void setSize_of_industry(UUID size_of_industry) {
        this.size_of_industry = size_of_industry;
    }
    public UUID getProjectype() {
        return projectype;
    }

    public void setProjectype(UUID projectype) {
        this.projectype = projectype;
    }
    public String getNiccode() {
        return niccode;
    }

    public void setNiccode(String niccode) {
        this.niccode = niccode;
    }
    public UUID getCategory_of_project() {
        return category_of_project;
    }

    public void setCategory_of_project(UUID category_of_project) {
        this.category_of_project = category_of_project;
    }
    public UUID getCollaboration_with_foreign_country() {
        return collaboration_with_foreign_country;
    }

    public void setCollaboration_with_foreign_country(UUID collaboration_with_foreign_country) {
        this.collaboration_with_foreign_country = collaboration_with_foreign_country;
    }
    public ByteBuffer getDetail_project_report() {
        return detail_project_report;
    }

    public void setDetail_project_report(ByteBuffer detail_project_report) {
        this.detail_project_report = detail_project_report;
    }

    public String getDetail_project_reportContentType() {
        return detail_project_reportContentType;
    }

    public void setDetail_project_reportContentType(String detail_project_reportContentType) {
        this.detail_project_reportContentType = detail_project_reportContentType;
    }
    public Boolean getExisting_regulatory_approval() {
        return existing_regulatory_approval;
    }

    public void setExisting_regulatory_approval(Boolean existing_regulatory_approval) {
        this.existing_regulatory_approval = existing_regulatory_approval;
    }
    public UUID getApproval_application_form() {
        return approval_application_form;
    }

    public void setApproval_application_form(UUID approval_application_form) {
        this.approval_application_form = approval_application_form;
    }
    public ByteBuffer getApproval_document() {
        return approval_document;
    }

    public void setApproval_document(ByteBuffer approval_document) {
        this.approval_document = approval_document;
    }

    public String getApproval_documentContentType() {
        return approval_documentContentType;
    }

    public void setApproval_documentContentType(String approval_documentContentType) {
        this.approval_documentContentType = approval_documentContentType;
    }
    public ByteBuffer getEdc_sif_clu_fee_paid_document() {
        return edc_sif_clu_fee_paid_document;
    }

    public void setEdc_sif_clu_fee_paid_document(ByteBuffer edc_sif_clu_fee_paid_document) {
        this.edc_sif_clu_fee_paid_document = edc_sif_clu_fee_paid_document;
    }

    public String getEdc_sif_clu_fee_paid_documentContentType() {
        return edc_sif_clu_fee_paid_documentContentType;
    }

    public void setEdc_sif_clu_fee_paid_documentContentType(String edc_sif_clu_fee_paid_documentContentType) {
        this.edc_sif_clu_fee_paid_documentContentType = edc_sif_clu_fee_paid_documentContentType;
    }
    public Boolean getEdc_sif_clu_fee_paid_applicable() {
        return edc_sif_clu_fee_paid_applicable;
    }

    public void setEdc_sif_clu_fee_paid_applicable(Boolean edc_sif_clu_fee_paid_applicable) {
        this.edc_sif_clu_fee_paid_applicable = edc_sif_clu_fee_paid_applicable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectdetailDTO projectdetailDTO = (ProjectdetailDTO) o;

        if ( ! Objects.equals(id, projectdetailDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectdetailDTO{" +
            "id=" + id +
            ", investorid='" + investorid + "'" +
            ", sector='" + sector + "'" +
            ", projectpurpose='" + projectpurpose + "'" +
            ", size_of_industry='" + size_of_industry + "'" +
            ", projectype='" + projectype + "'" +
            ", niccode='" + niccode + "'" +
            ", category_of_project='" + category_of_project + "'" +
            ", collaboration_with_foreign_country='" + collaboration_with_foreign_country + "'" +
            ", detail_project_report='" + detail_project_report + "'" +
            ", existing_regulatory_approval='" + existing_regulatory_approval + "'" +
            ", approval_application_form='" + approval_application_form + "'" +
            ", approval_document='" + approval_document + "'" +
            ", edc_sif_clu_fee_paid_document='" + edc_sif_clu_fee_paid_document + "'" +
            ", edc_sif_clu_fee_paid_applicable='" + edc_sif_clu_fee_paid_applicable + "'" +
            '}';
    }
}