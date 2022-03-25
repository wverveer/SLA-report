package com.keylane;

import java.util.Date;

/**
 * This class is a Data Model which contains all the information of a single SLA record.
 */
public class SLARecord {
    private final Date timestamp;
    private final String serviceName;
    private final int durationInMs;

    public SLARecord(Date timestamp, String serviceName, int durationInMs) {
        this.timestamp = timestamp;
        this.serviceName = serviceName;
        this.durationInMs = durationInMs;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getDurationInMs() {
        return durationInMs;
    }
}
