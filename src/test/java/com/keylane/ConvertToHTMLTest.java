package com.keylane;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConvertToHTMLTest {

    @Test
    void tester() {
        List<SLARecord> list = new ArrayList<>();
        list.add(new SLARecord(null,"test",100));
        list.add(new SLARecord(null,"test",300));
        list.add(new SLARecord(null,"test",300));
        list.add(new SLARecord(null,"test",100));
        list.add(new SLARecord(null,"test",400));
        list.add(new SLARecord(null,"test",600));

        ConvertToHTML convert = new ConvertToHTML();
        List<String> result = convert.tester(list);
        System.out.print(result);
    }
}