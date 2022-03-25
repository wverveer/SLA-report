package com.keylane;

import java.util.List;

public interface ConvertToSLARecord {
    List<SLARecord> convert(String inputFilePath);
}
