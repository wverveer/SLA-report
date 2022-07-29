package com.keylane.fileWriter;

import java.util.List;

public interface FileWriter {

    void write(List<String> output, String filename) throws Exception;
}
