package com.vv.personal.twm.health.service.model;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.Instant;

/**
 * @author Vivek
 * @since 12/11/21
 */
@Getter
@Builder
public class HealthCharter {

    private String person;
    private Instant date;
    private Double weight;
    private Integer fastingBloodSugar;
    private Integer postPrandialBloodSugar;
    private Integer systolicBP;
    private Integer diastolicBP;
    private Integer pulse;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
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
}