package com.keylane.reader;

import com.keylane.model.SLARecord;

import java.io.IOException;
import java.util.List;

public interface ConvertToSLARecord {
    List<SLARecord> convert(String inputFilePath) throws IOException, Exception;
}
