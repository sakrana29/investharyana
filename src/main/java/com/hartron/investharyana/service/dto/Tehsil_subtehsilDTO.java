package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Tehsil_subtehsil entity.
 */
public class Tehsil_subtehsilDTO implements Serializable {

    private UUID id;

    private UUID districtid;

    private String tehsil_subtehsilname;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getDistrictid() {
        return districtid;
    }

    public void setDistrictid(UUID districtid) {
        this.districtid = districtid;
    }
    public String getTehsil_subtehsilname() {
        return tehsil_subtehsilname;
    }

    public void setTehsil_subtehsilname(String tehsil_subtehsilname) {
        this.tehsil_subtehsilname = tehsil_subtehsilname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Tehsil_subtehsilDTO tehsil_subtehsilDTO = (Tehsil_subtehsilDTO) o;

        if ( ! Objects.equals(id, tehsil_subtehsilDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Tehsil_subtehsilDTO{" +
            "id=" + id +
            ", districtid='" + districtid + "'" +
            ", tehsil_subtehsilname='" + tehsil_subtehsilname + "'" +
            '}';
    }
}