package com.keylane;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CSVConverterTest {

    @Test
    void convert() throws ParseException {
        List<SLARecord> slaRecordList = new ArrayList<>();
        slaRecordList.add(new SLARecord(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-02-09 04:19:12"), "PolicyService", 968));
        slaRecordList.add(new SLARecord(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-02-09 04:19:12"), "PolicyService", 345));
        slaRecordList.add(new SLARecord(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-02-09 04:19:12"), "FinancialService", 562));
        slaRecordList.add(new SLARecord(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-02-09 04:19:12"), "TestService", 968));
        slaRecordList.add(new SLARecord(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-02-09 04:19:12"), "TestService", 345));
        slaRecordList.add(new SLARecord(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-02-09 04:19:12"), "TestService", 562));

        CSVConverter csvConverter = new CSVConverter();
        List<String> actual = csvConverter.convert(slaRecordList);

        assertEquals(3, actual.size());
        assertEquals("TestService;625", actual.get(0));
        assertEquals("PolicyService;656.5", actual.get(1));
        assertEquals("FinancialService;562", actual.get(2));

    }
}