package com.keylane;

import java.io.IOException;
import java.util.List;

public interface ConvertToSLARecord {
    List<SLARecord> convert(String inputFilePath) throws IOException, Exception;
}
