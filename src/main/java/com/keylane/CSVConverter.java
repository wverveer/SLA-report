package com.keylane;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CSVConverter implements ConvertFromSLARecord {
    static class ServiceRecord {
        private final String service;
        private int sum;
        private int count;

        ServiceRecord(String service, int sum, int count) {
            this.service = service;
            this.sum = sum;
            this.count = count;
        }

        public String getService() {
            return service;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    @Override
    public List<String> convert(List<SLARecord> input) {
        List<String> output = new ArrayList<>();
        HashMap<String, ServiceRecord> serviceRecordsHashMap = new HashMap<>();

        for (SLARecord record : input) {
            String serviceName = record.getServiceName();
            int duration = record.getDurationInMs();
            if (serviceRecordsHashMap.containsKey(serviceName)) {
                ServiceRecord serviceRecord = serviceRecordsHashMap.get(serviceName);

                serviceRecord.setSum(duration + serviceRecord.getSum());
                serviceRecord.setCount(serviceRecord.getCount() + 1);
            } else {
                ServiceRecord serviceRecord = new ServiceRecord(serviceName, duration, 1);
                serviceRecordsHashMap.put(serviceName, serviceRecord);
            }
        }

        for (ServiceRecord record : serviceRecordsHashMap.values()) {
            double average = (double) record.getSum() / (double) record.getCount();
            DecimalFormat format = new DecimalFormat("0.#");
            output.add(record.getService() + ";" + format.format(average));
        }

        return output;
    }

}
