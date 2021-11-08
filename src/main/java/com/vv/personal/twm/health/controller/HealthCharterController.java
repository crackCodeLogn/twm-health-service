package com.vv.personal.twm.health.controller;

import com.vv.personal.twm.health.interactor.twm.health.tables.HealthCharter;
import com.vv.personal.twm.health.model.HealthCharterEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import static com.vv.personal.twm.health.constants.Constants.DTF_ENTRY_DAY_DATE_PATTERN;

/**
 * @author Vivek
 * @since 08/11/21
 */
@Slf4j
@RestController("twm-health-charter-controller")
@RequestMapping("health")
@Secured("user")
public class HealthCharterController {

    @Inject
    HealthCharter healthCharter;

    @GetMapping(value = "manual/create/entry")
    public int createEntryManually(@RequestParam("person") String person,
                                   @RequestParam("weight") Double weight,
                                   @RequestParam("sugar-fasting") Integer fbs,
                                   @RequestParam("sugar-post-lunch") Integer pp2bs,
                                   @RequestParam("bp-high") Integer bpHigh,
                                   @RequestParam("bp-low") Integer bpLow,
                                   @RequestParam("bp-pulse") Integer pulse,
                                   @RequestParam(value = "date_day", required = false) Integer day,
                                   @RequestParam(value = "date_month", required = false) Integer month,
                                   @RequestParam(value = "date_year", required = false) Integer year) {
        Instant instant;
        if (day == null) instant = Instant.now();
        else instant = LocalDate.of(year, month, day).atTime(0, 0, 0).atZone(ZoneId.of("UTC")).toInstant();

        HealthCharterEntity healthCharterEntity = new HealthCharterEntity()
                .setPersonAndDate(generatePersonAndDate(person, instant))
                .setPerson(person).setDate(instant)
                .setWeight(weight)
                .setFastingBloodSugar(getDefaultedInteger(fbs))
                .setPostPrandialBloodSugar(getDefaultedInteger(pp2bs))
                .setSystolicBP(getDefaultedInteger(bpHigh))
                .setDiastolicBP(getDefaultedInteger(bpLow))
                .setPulse(getDefaultedInteger(pulse));

        log.info("Going to save => '{}'", healthCharterEntity);
        int saveResult = healthCharter.pushNewEntity(healthCharterEntity);
        log.info("Save result: {}", saveResult);
        return saveResult;
    }

    private String generatePersonAndDate(String person, Instant date) {
        return person + "^" + date;
    }

    private Integer getDefaultedInteger(Integer val) {
        return val != null ? val : -1;
    }
}