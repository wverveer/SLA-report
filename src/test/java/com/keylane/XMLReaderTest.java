package com.keylane;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

class XMLReaderTest {

    @Test
    void convert() throws Exception {
        XMLReader reader = new XMLReader();
        String inputFilePath = Objects.requireNonNull(getClass().getClassLoader().getResource("input.xml")).getPath();

        List<SLARecord> actual = reader.convert(inputFilePath);

        assertEquals(3, actual.size());
        assertSLARecordEquals(createTestSLARecord("2022-02-09 04:19:12", "PolicyService", 968), actual.get(0));
        assertSLARecordEquals(createTestSLARecord("2022-02-09 04:19:25", "PolicyService", 654), actual.get(1));
        assertSLARecordEquals(createTestSLARecord("2022-02-09 06:09:55", "FinancialService", 344), actual.get(2));
    }

    private SLARecord createTestSLARecord(String date, String service, int durationInMS) throws ParseException {
        return new SLARecord(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date), service, durationInMS);
    }

    private void assertSLARecordEquals(SLARecord expected, SLARecord actual) {
        assertEquals(expected.getServiceName(), actual.getServiceName());
        assertEquals(expected.getDurationInMs(), actual.getDurationInMs());
        assertEquals(expected.getTimestamp(), actual.getTimestamp());
    }
}