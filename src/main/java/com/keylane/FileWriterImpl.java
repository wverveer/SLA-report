package com.keylane;

import java.io.File;
import java.util.List;

/*  This class receives a List of Strings and a filename as a destination,
*   and it creates the corresponding file as output.
*/

public class FileWriterImpl implements FileWriter {
    @Override
    public void write(List<String> output, String filename) throws Exception {
        File myObj = new File(filename);
        myObj.createNewFile();

        java.io.FileWriter myWriter = new java.io.FileWriter(filename);

        for (String s : output) {
            myWriter.write(s+"\n");
        }
        myWriter.close();
    }
}