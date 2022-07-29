package com.keylane.converter;

import java.util.List;

import com.keylane.model.SLARecord;

public interface ConvertFromSLARecord {
    List<String> convert(List<SLARecord> input) throws Exception;
}
