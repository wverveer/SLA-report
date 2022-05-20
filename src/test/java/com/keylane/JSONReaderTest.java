package com.keylane;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class JSONReaderTest {

    @Test
    void convert() throws Exception {
        JSONReader reader = new JSONReader();
        String inputFilePath = Objects.requireNonNull(getClass().getClassLoader().getResource("ConvertFromJSON/input.json")).getPath();

        List<SLARecord> actual = reader.convert(inputFilePath);

        assertEquals(12, actual.size());
        assertSLARecordEquals(createTestSLARecord("2022-02-09 04:19:12", "PolicyService", 968), actual.get(0));
        assertSLARecordEquals(createTestSLARecord("2022-02-09 04:19:25", "PolicyService", 654), actual.get(1));
        assertSLARecordEquals(createTestSLARecord("2022-02-09 06:09:55", "FinancialService", 344), actual.get(2));
        assertSLARecordEquals(createTestSLARecord("2022-02-09 06:45:34", "PolicyService", 571), actual.get(3));
        assertSLARecordEquals(createTestSLARecord("2022-02-09 12:57:06", "PolicyService", 691), actual.get(4));
        assertSLARecordEquals(createTestSLARecord("2022-02-09 13:43:53", "FinancialService", 701), actual.get(5));
        assertSLARecordEquals(createTestSLARecord("2022-02-09 14:45:43", "PolicyService", 632), actual.get(6));
        assertSLARecordEquals(createTestSLARecord("2022-02-09 15:43:53", "PolicyService", 240), actual.get(7));
        assertSLARecordEquals(createTestSLARecord("2022-02-09 19:20:39", "PolicyService", 484), actual.get(8));
        assertSLARecordEquals(createTestSLARecord("2022-02-09 22:49:59", "PolicyService", 844), actual.get(9));
        assertSLARecordEquals(createTestSLARecord("2022-02-09 22:49:59", "FinancialService", 353), actual.get(10));
        assertSLARecordEquals(createTestSLARecord("2022-02-09 22:49:59", "PolicyService", 351), actual.get(11));
    }

    @Test
    void ignoreNullValuesForService() throws Exception {
        JSONReader reader = new JSONReader();
        String inputFilePath = Objects.requireNonNull(getClass().getClassLoader().getResource("ConvertFromJSON/ignoreNullValuesForService.json")).getPath();

        List<SLARecord> actual = reader.convert(inputFilePath);

        assertEquals(1, actual.size());
        assertSLARecordEquals(createTestSLARecord("2022-02-09 14:45:43", "", 968), actual.get(0));
    }

    @Test
    void exceptionWhenDateWrongFormat() {
        JSONReader reader = new JSONReader();
        String inputFilePath = Objects.requireNonNull(getClass().getClassLoader().getResource("ConvertFromJSON/exceptionWhenDateWrongFormat.json")).getPath();

        assertThrows(ParseException.class, () -> reader.convert(inputFilePath));
    }

    @Test
    void exceptionWhenTagCannotBeFound() {
        JSONReader reader = new JSONReader();
        String inputFilePath = Objects.requireNonNull(getClass().getClassLoader().getResource("ConvertFromJSON/exceptionWhenTagCannotBeFound.json")).getPath();

        assertThrows(NullPointerException.class, () -> reader.convert(inputFilePath));
    }

    @Test
    void exceptionWhenDurationNotInt() {
        JSONReader reader = new JSONReader();
        String inputFilePath = Objects.requireNonNull(getClass().getClassLoader().getResource("ConvertFromJSON/exceptionWhenDurationNotInt.json")).getPath();

        assertThrows(NumberFormatException.class, () -> reader.convert(inputFilePath));
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





































