package com.vv.personal.twm.health.service;

import com.vv.personal.twm.health.service.model.HealthCharter;

import java.util.List;

/**
 * @author Vivek
 * @since 12/11/21
 */
public interface HealthCharterService {

    int pushNewEntry(HealthCharter healthCharter);

    List<HealthCharter> retrieveAllEntries();

    String processDataToCsv(String delimiter);

    int restoreFromBackup(String srcFilePath, String delimiter);
}