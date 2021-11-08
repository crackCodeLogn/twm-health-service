package com.vv.personal.twm.health.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;

/**
 * @author Vivek
 * @since 08/11/21
 */
@Entity
@Table(name = "health_charter")
public class HealthCharterEntity implements Serializable {
    private static final long serialVersionUID = 8370023146273452293L;

    @Id
    @Column(name = "psn_dt", nullable = false)
    private String personAndDate;

    @Column(name = "psn", nullable = false)
    private String person;

    @Column(name = "dt", nullable = false)
    private Instant date;

    @Column(name = "wt", nullable = false)
    private Double weight;

    @Column(name = "fbs")
    private Integer fastingBloodSugar;

    @Column(name = "pp2bs")
    private Integer postPrandialBloodSugar;

    @Column(name = "sys")
    private Integer systolicBP;

    @Column(name = "dia")
    private Integer diastolicBP;

    @Column(name = "pulse")
    private Integer pulse;

    public HealthCharterEntity() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("personAndDate", personAndDate)
                .append("person", person)
                .append("date", date)
                .append("weight", weight)
                .append("fastingBloodSugar", fastingBloodSugar)
                .append("postPrandialBloodSugar", postPrandialBloodSugar)
                .append("systolicBP", systolicBP)
                .append("diastolicBP", diastolicBP)
                .append("pulse", pulse)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        HealthCharterEntity that = (HealthCharterEntity) o;

        return new EqualsBuilder().append(personAndDate, that.personAndDate).append(person, that.person).append(date, that.date).append(weight, that.weight).append(fastingBloodSugar, that.fastingBloodSugar).append(postPrandialBloodSugar, that.postPrandialBloodSugar).append(systolicBP, that.systolicBP).append(diastolicBP, that.diastolicBP).append(pulse, that.pulse).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(personAndDate).append(person).append(date).append(weight).append(fastingBloodSugar).append(postPrandialBloodSugar).append(systolicBP).append(diastolicBP).append(pulse).toHashCode();
    }

    public String getPersonAndDate() {
        return personAndDate;
    }

    public HealthCharterEntity setPersonAndDate(String personAndDate) {
        this.personAndDate = personAndDate;
        return this;
    }

    public String getPerson() {
        return person;
    }

    public HealthCharterEntity setPerson(String person) {
        this.person = person;
        return this;
    }

    public Instant getDate() {
        return date;
    }

    public HealthCharterEntity setDate(Instant date) {
        this.date = date;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public HealthCharterEntity setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public Integer getFastingBloodSugar() {
        return fastingBloodSugar;
    }

    public HealthCharterEntity setFastingBloodSugar(Integer fastingBloodSugar) {
        this.fastingBloodSugar = fastingBloodSugar;
        return this;
    }

    public Integer getPostPrandialBloodSugar() {
        return postPrandialBloodSugar;
    }

    public HealthCharterEntity setPostPrandialBloodSugar(Integer postPrandialBloodSugar) {
        this.postPrandialBloodSugar = postPrandialBloodSugar;
        return this;
    }

    public Integer getSystolicBP() {
        return systolicBP;
    }

    public HealthCharterEntity setSystolicBP(Integer systolicBP) {
        this.systolicBP = systolicBP;
        return this;
    }

    public Integer getDiastolicBP() {
        return diastolicBP;
    }

    public HealthCharterEntity setDiastolicBP(Integer diastolicBP) {
        this.diastolicBP = diastolicBP;
        return this;
    }

    public Integer getPulse() {
        return pulse;
    }

    public HealthCharterEntity setPulse(Integer pulse) {
        this.pulse = pulse;
        return this;
    }
}