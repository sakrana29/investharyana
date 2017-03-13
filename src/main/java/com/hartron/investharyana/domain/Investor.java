package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * A Investor.
 */

@Table(name = "investor")
public class Investor implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private Boolean mouapplicable;

    private Integer mousignyear;

    private ByteBuffer moudocument;

    @Column(name = "moudocument_content_type")
    private String moudocumentContentType;

    private String mouidnumber;

    private ByteBuffer photo;

    @Column(name = "photo_content_type")
    private String photoContentType;

    private String firstname;

    private String middlename;

    private String lastname;

    private UUID countryid;

    private UUID stateid;

    private UUID cityid;

    private String address1;

    private String address2;

    private String address3;

    private String emailprimary;

    private String emailsecondary;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Boolean isMouapplicable() {
        return mouapplicable;
    }

    public Investor mouapplicable(Boolean mouapplicable) {
        this.mouapplicable = mouapplicable;
        return this;
    }

    public void setMouapplicable(Boolean mouapplicable) {
        this.mouapplicable = mouapplicable;
    }

    public Integer getMousignyear() {
        return mousignyear;
    }

    public Investor mousignyear(Integer mousignyear) {
        this.mousignyear = mousignyear;
        return this;
    }

    public void setMousignyear(Integer mousignyear) {
        this.mousignyear = mousignyear;
    }

    public ByteBuffer getMoudocument() {
        return moudocument;
    }

    public Investor moudocument(ByteBuffer moudocument) {
        this.moudocument = moudocument;
        return this;
    }

    public void setMoudocument(ByteBuffer moudocument) {
        this.moudocument = moudocument;
    }

    public String getMoudocumentContentType() {
        return moudocumentContentType;
    }

    public Investor moudocumentContentType(String moudocumentContentType) {
        this.moudocumentContentType = moudocumentContentType;
        return this;
    }

    public void setMoudocumentContentType(String moudocumentContentType) {
        this.moudocumentContentType = moudocumentContentType;
    }

    public String getMouidnumber() {
        return mouidnumber;
    }

    public Investor mouidnumber(String mouidnumber) {
        this.mouidnumber = mouidnumber;
        return this;
    }

    public void setMouidnumber(String mouidnumber) {
        this.mouidnumber = mouidnumber;
    }

    public ByteBuffer getPhoto() {
        return photo;
    }

    public Investor photo(ByteBuffer photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(ByteBuffer photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public Investor photoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
        return this;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public String getFirstname() {
        return firstname;
    }

    public Investor firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public Investor middlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public Investor lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public UUID getCountryid() {
        return countryid;
    }

    public Investor countryid(UUID countryid) {
        this.countryid = countryid;
        return this;
    }

    public void setCountryid(UUID countryid) {
        this.countryid = countryid;
    }

    public UUID getStateid() {
        return stateid;
    }

    public Investor stateid(UUID stateid) {
        this.stateid = stateid;
        return this;
    }

    public void setStateid(UUID stateid) {
        this.stateid = stateid;
    }

    public UUID getCityid() {
        return cityid;
    }

    public Investor cityid(UUID cityid) {
        this.cityid = cityid;
        return this;
    }

    public void setCityid(UUID cityid) {
        this.cityid = cityid;
    }

    public String getAddress1() {
        return address1;
    }

    public Investor address1(String address1) {
        this.address1 = address1;
        return this;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public Investor address2(String address2) {
        this.address2 = address2;
        return this;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public Investor address3(String address3) {
        this.address3 = address3;
        return this;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getEmailprimary() {
        return emailprimary;
    }

    public Investor emailprimary(String emailprimary) {
        this.emailprimary = emailprimary;
        return this;
    }

    public void setEmailprimary(String emailprimary) {
        this.emailprimary = emailprimary;
    }

    public String getEmailsecondary() {
        return emailsecondary;
    }

    public Investor emailsecondary(String emailsecondary) {
        this.emailsecondary = emailsecondary;
        return this;
    }

    public void setEmailsecondary(String emailsecondary) {
        this.emailsecondary = emailsecondary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Investor investor = (Investor) o;
        if (investor.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, investor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Investor{" +
            "id=" + id +
            ", mouapplicable='" + mouapplicable + "'" +
            ", mousignyear='" + mousignyear + "'" +
            ", moudocument='" + moudocument + "'" +
            ", moudocumentContentType='" + moudocumentContentType + "'" +
            ", mouidnumber='" + mouidnumber + "'" +
            ", photo='" + photo + "'" +
            ", photoContentType='" + photoContentType + "'" +
            ", firstname='" + firstname + "'" +
            ", middlename='" + middlename + "'" +
            ", lastname='" + lastname + "'" +
            ", countryid='" + countryid + "'" +
            ", stateid='" + stateid + "'" +
            ", cityid='" + cityid + "'" +
            ", address1='" + address1 + "'" +
            ", address2='" + address2 + "'" +
            ", address3='" + address3 + "'" +
            ", emailprimary='" + emailprimary + "'" +
            ", emailsecondary='" + emailsecondary + "'" +
            '}';
    }
}