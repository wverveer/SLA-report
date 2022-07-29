package com.keylane.converter;

import com.keylane.model.SLARecord;

import java.util.List;

public interface ConvertFromSLARecord {
    List<String> convert(List<SLARecord> input) throws Exception;
}
