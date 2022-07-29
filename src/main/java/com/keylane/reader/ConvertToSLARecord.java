package com.keylane.reader;

import java.util.List;

import com.keylane.model.SLARecord;

public interface ConvertToSLARecord {
    List<SLARecord> convert(String inputFilePath) throws Exception;
}
