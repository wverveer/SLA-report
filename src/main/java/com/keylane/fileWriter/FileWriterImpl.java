package com.keylane.fileWriter;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.List;

/*  This class receives a List of Strings and a filename as a destination,
 *   and it creates the corresponding file as output.
 */

public class FileWriterImpl implements FileWriter {
    @Override
    public void write(List<String> output, String filename) throws Exception {
        try (java.io.FileWriter myWriter = new java.io.FileWriter(filename);
             BufferedWriter outputBufferedWriter = new BufferedWriter(myWriter);
             PrintWriter writer = new PrintWriter(outputBufferedWriter)) {
            output.forEach(writer::println);
        }
    }
}