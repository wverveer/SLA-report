package com.keylane;

import java.io.FileReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CSVReader implements ConvertToSLARecord {
    enum Headers {
        Timestamp, Service, Duration
    }

    @Override
    public List<SLARecord> convert(String inputFilePath) throws Exception {
        List<SLARecord> SLARecordsList = new ArrayList<>();

        List<CSVRecord> CSVRecordObjectList = createObjectListFromInput(inputFilePath);
        for (CSVRecord record : CSVRecordObjectList) {
            SLARecordsList.add(createSLARecord(record));
        }

        return SLARecordsList;
    }

    private List<CSVRecord> createObjectListFromInput(String inputFilePath) throws Exception {
        List<CSVRecord> CSVObjectList = new ArrayList<>();
        try (Reader reader = new FileReader(inputFilePath)) {
            Iterable<CSVRecord> records = CSVFormat.EXCEL.builder()
                    .setHeader(Headers.class)
                    .setSkipHeaderRecord(true)
                    .setIgnoreEmptyLines(true)
                    .setDelimiter(";")
                    .build().parse(reader);
            for (Object object : records) {
                CSVObjectList.add((CSVRecord) object);
            }
        }
        return CSVObjectList;
    }


    private SLARecord createSLARecord(CSVRecord csvRecord) throws Exception {
        Date timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(csvRecord.get("Timestamp").toString());
        String serviceName = csvRecord.get("Service").toString();
        int durationInMs = Integer.parseInt(csvRecord.get("Duration").toString());

        return new SLARecord(timestamp, serviceName, durationInMs);
    }

}
