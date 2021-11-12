package com.vv.personal.twm.health.controller;

import com.vv.personal.twm.health.config.BeanStore;
import com.vv.personal.twm.health.service.HealthCharterService;
import com.vv.personal.twm.health.service.model.HealthCharter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

/**
 * @author Vivek
 * @since 08/11/21
 */
@Slf4j
@RestController("twm-health-charter-controller")
@RequestMapping("health")
@Secured("user")
public class HealthCharterController {
    //TODO - convert this controller to swagger impl

    @Inject
    BeanStore beanStore;
    @Inject
    HealthCharterService healthCharterService;

    @PutMapping(value = "manual/create/entry")
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
        StopWatch stopWatch = beanStore.procureStopWatch();
        Instant instant;
        if (day == null) instant = Instant.now();
        else instant = LocalDate.of(year, month, day).atTime(0, 0, 0).atZone(ZoneId.of("UTC")).toInstant();

        HealthCharter healthCharter = HealthCharter.builder()
                .person(person)
                .date(instant)
                .weight(weight)
                .fastingBloodSugar(getDefaultedInteger(fbs))
                .postPrandialBloodSugar(getDefaultedInteger(pp2bs))
                .systolicBP(getDefaultedInteger(bpHigh))
                .diastolicBP(getDefaultedInteger(bpLow))
                .pulse(getDefaultedInteger(pulse))
                .build();

        log.info("Going to save => '{}'", healthCharter);
        int saveResult = healthCharterService.pushNewEntry(healthCharter);
        stopWatch.stop();
        log.info("Save result in {} ms: {}", stopWatch.getTime(TimeUnit.MILLISECONDS), saveResult);
        return saveResult;
    }

    @GetMapping(value = "manual/backup", produces = "text/plain")
    public String retrieveBackup(@RequestParam(name = "delimiter", defaultValue = ",") String delimiter) {
        StopWatch stopWatch = beanStore.procureStopWatch();
        try {
            return healthCharterService.processDataToCsv(delimiter);
        } finally {
            stopWatch.stop();
            log.info("Manual Backup csv operation done in {} ms", stopWatch.getTime(TimeUnit.MILLISECONDS));
        }
    }

    @PutMapping(value = "manual/restore")
    public int restoreFromBackup(@RequestParam(name = "srcFilePath") String srcFilePath,
                                 @RequestParam(value = "delimiter", defaultValue = ",") String delimiter) {
        StopWatch stopWatch = beanStore.procureStopWatch();
        try {
            return healthCharterService.restoreFromBackup(srcFilePath, delimiter);
        } finally {
            stopWatch.stop();
            log.info("Restoration from Backup csv operation done in {} ms", stopWatch.getTime(TimeUnit.MILLISECONDS));
        }
    }

    private Integer getDefaultedInteger(Integer val) {
        return val != null ? val : -1;
    }
}