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
    void convert() throws ParseException {
        XMLReader reader = new XMLReader();

        List<SLARecord> input = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date timestamp = format.parse("2022-02-09 04:19:12");
        input.add(new SLARecord(timestamp, "PolicyService", 968));

        timestamp = format.parse("2022-02-09 04:19:25");
        input.add(new SLARecord(timestamp, "PolicyService", 654));

        timestamp = format.parse("2022-02-09 06:09:55");
        input.add(new SLARecord(timestamp, "FinancialService", 344));

        List<SLARecord> output = reader.convert(Objects.requireNonNull(getClass().getClassLoader().getResource("input.xml")).getPath());

        for (int i=0; i<input.size(); i++){
            assertEquals(input.get(i).getDurationInMs(), output.get(i).getDurationInMs());
            assertEquals(input.get(i).getServiceName(), output.get(i).getServiceName());
            assertEquals(input.get(i).getTimestamp(), output.get(i).getTimestamp());
        }
    }
}