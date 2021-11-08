package com.vv.personal.twm.health.interactor.twm.health.tables;

import com.vv.personal.twm.health.model.HealthCharterEntity;
import com.vv.personal.twm.health.repository.HealthCharterRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static com.vv.personal.twm.health.constants.Constants.*;

/**
 * @author Vivek
 * @since 08/11/21
 */
@Slf4j
public class HealthCharter {

    private final HealthCharterRepository healthCharterRepository;

    public HealthCharter(HealthCharterRepository healthCharterRepository) {
        this.healthCharterRepository = healthCharterRepository;
    }

    public int pushNewEntity(HealthCharterEntity healthCharterEntity) {
        try {
            healthCharterRepository.save(healthCharterEntity);
            return ONE;
        } catch (Exception e) {
            log.error("Failed to push new entity to db: '{}'. ", healthCharterEntity, e);
        }
        return NA_INT;
    }

    public List<HealthCharterEntity> retrieveAllEntities() {
        try {
            return healthCharterRepository.findAll();
        } catch (Exception e) {
            log.error("Failed to retrieve all entries. ", e);
        }
        return new ArrayList<>();
    }

    public String processDataToCsv(String delimiter) {
        StringBuilder dataLines = new StringBuilder();
        retrieveAllEntities().forEach(healthCharter ->
                dataLines.append(StringUtils.joinWith(delimiter,
                                healthCharter.getPersonAndDate(), healthCharter.getPerson(), String.valueOf(healthCharter.getDate()), getStringValue(healthCharter.getWeight()),
                                getStringValue(healthCharter.getFastingBloodSugar()), getStringValue(healthCharter.getPostPrandialBloodSugar()),
                                getStringValue(healthCharter.getSystolicBP()), getStringValue(healthCharter.getDiastolicBP()), getStringValue(healthCharter.getPulse())))
                        .append(NEW_LINE)
        );
        return dataLines.toString();
    }

    private String getStringValue(Integer val) {
        return String.valueOf(val);
    }

    private String getStringValue(Double val) {
        return String.valueOf(val);
    }
}