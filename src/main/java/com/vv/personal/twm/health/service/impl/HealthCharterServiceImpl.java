package com.vv.personal.twm.health.service.impl;

import com.vv.personal.twm.health.model.HealthCharterEntity;
import com.vv.personal.twm.health.repository.HealthCharterRepository;
import com.vv.personal.twm.health.service.HealthCharterService;
import com.vv.personal.twm.health.service.model.HealthCharter;
import com.vv.personal.twm.health.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.vv.personal.twm.health.constants.Constants.*;

/**
 * @author Vivek
 * @since 08/11/21
 */
@Slf4j
@Service
public class HealthCharterServiceImpl implements HealthCharterService {

    private final HealthCharterRepository healthCharterRepository;

    public HealthCharterServiceImpl(HealthCharterRepository healthCharterRepository) {
        this.healthCharterRepository = healthCharterRepository;
    }

    @Override
    public int pushNewEntry(HealthCharter healthCharter) {
        try {
            HealthCharterEntity healthCharterEntity = generateHealthCharterEntity(healthCharter);
            healthCharterRepository.save(healthCharterEntity);
            return ONE;
        } catch (Exception e) {
            log.error("Failed to push new data to db: '{}'. ", healthCharter, e);
        }
        return NA_INT;
    }

    @Override
    public List<HealthCharter> retrieveAllEntries() {
        try {
            return retrieveAllEntities().stream()
                    .map(this::generateHealthCharter).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Failed to retrieve all entries. ", e);
        }
        return new ArrayList<>();
    }

    @Override
    public String processDataToCsv(String delimiter) {
        StringBuilder dataLines = new StringBuilder();
        //direct hit repo
        retrieveAllEntities().forEach(healthCharter ->
                dataLines.append(StringUtils.joinWith(delimiter,
                                healthCharter.getPersonAndDate(), healthCharter.getPerson(), String.valueOf(healthCharter.getDate()), getStringValue(healthCharter.getWeight()),
                                getStringValue(healthCharter.getFastingBloodSugar()), getStringValue(healthCharter.getPostPrandialBloodSugar()),
                                getStringValue(healthCharter.getSystolicBP()), getStringValue(healthCharter.getDiastolicBP()), getStringValue(healthCharter.getPulse())))
                        .append(NEW_LINE)
        );
        return dataLines.toString();
    }

    @Override
    public int restoreFromBackup(String srcFilePath, String delimiter) {
        List<HealthCharterEntity> healthCharterEntities = FileUtil.readFileFromLocation(srcFilePath).stream()
                .map(data -> {
                    String[] vals = data.split(delimiter);

                    String personAndDate = vals[0];
                    String person = vals[1];
                    Instant date = Instant.parse(vals[2].trim());
                    double weight = Double.parseDouble(vals[3].trim());
                    int fbs = Integer.parseInt(vals[4].trim());
                    int pp2bs = Integer.parseInt(vals[5].trim());
                    int sys = Integer.parseInt(vals[6].trim());
                    int dia = Integer.parseInt(vals[7].trim());
                    int pulse = Integer.parseInt(vals[8].trim());
                    return new HealthCharterEntity()
                            .setPersonAndDate(personAndDate)
                            .setPerson(person)
                            .setDate(date)
                            .setWeight(weight)
                            .setFastingBloodSugar(fbs)
                            .setPostPrandialBloodSugar(pp2bs)
                            .setSystolicBP(sys)
                            .setDiastolicBP(dia)
                            .setPulse(pulse)
                            ;
                }).collect(Collectors.toList());
        log.info("Extracted {} entities from '{}'", healthCharterEntities.size(), srcFilePath);
        int saved = healthCharterRepository.saveAll(healthCharterEntities).size();
        log.info("Saved {} into db", saved);
        return saved;
    }

    private List<HealthCharterEntity> retrieveAllEntities() {
        return healthCharterRepository.findAll();
    }

    HealthCharterEntity generateHealthCharterEntity(HealthCharter healthCharter) {
        return new HealthCharterEntity()
                .setPersonAndDate(generatePersonAndDate(healthCharter.getPerson(), healthCharter.getDate()))
                .setDate(healthCharter.getDate())
                .setPerson(healthCharter.getPerson())
                .setWeight(healthCharter.getWeight())
                .setDiastolicBP(healthCharter.getDiastolicBP())
                .setSystolicBP(healthCharter.getSystolicBP())
                .setFastingBloodSugar(healthCharter.getFastingBloodSugar())
                .setPostPrandialBloodSugar(healthCharter.getPostPrandialBloodSugar())
                .setPulse(healthCharter.getPulse());
    }

    HealthCharter generateHealthCharter(HealthCharterEntity healthCharterEntity) {
        return HealthCharter.builder()
                .date(healthCharterEntity.getDate())
                .person(healthCharterEntity.getPerson())
                .weight(healthCharterEntity.getWeight())
                .diastolicBP(healthCharterEntity.getDiastolicBP())
                .systolicBP(healthCharterEntity.getSystolicBP())
                .fastingBloodSugar(healthCharterEntity.getFastingBloodSugar())
                .postPrandialBloodSugar(healthCharterEntity.getPostPrandialBloodSugar())
                .pulse(healthCharterEntity.getPulse())
                .build();
    }

    private String generatePersonAndDate(String person, Instant date) {
        return person + "^" + date;
    }

    private String getStringValue(Integer val) {
        return String.valueOf(val);
    }

    private String getStringValue(Double val) {
        return String.valueOf(val);
    }
}