package com.keylane;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class FileWriterImplTest {

    @Test
    void write() throws Exception {
        FileWriterImpl fw = new FileWriterImpl();
        List<String> input = new ArrayList<>();

        input.add("Random");
        input.add("text");
        input.add("is inserted");
        input.add("here.");

        //Manually enable the check locally
//        fw.write(input, "C:/temp/SLAReport/test.csv");

//        File myObj = new File("C:/temp/SLAReport/test.csv");
//        Scanner myReader = new Scanner(myObj);
//
//        String data = myReader.nextLine();
//        assertEquals("Random", data);
//
//        data = myReader.nextLine();
//        assertEquals("text", data);
//
//        data = myReader.nextLine();
//        assertEquals("is inserted", data);
//
//        data = myReader.nextLine();
//        assertEquals("here.", data);
    }

    @Test
    void overwrite() throws Exception {
        FileWriterImpl fw = new FileWriterImpl();
        List<String> input = new ArrayList<>();

        input.add("Initial");
        input.add("message.");
        //Manually enable the check locally
//        fw.write(input, "C:/temp/SLAReport/overwrite.csv");

        List<String> repeatedInput = new ArrayList<>();
        repeatedInput.add("Repeat");
        repeatedInput.add("procedure.");

//        fw.write(repeatedInput, "C:/temp/SLAReport/overwrite.csv");

//        File myObj = new File("C:/temp/SLAReport/overwrite.csv");
//        Scanner myReader = new Scanner(myObj);
//
//        String data = myReader.nextLine();
//        assertEquals("Repeat", data);
//
//        data = myReader.nextLine();
//        assertEquals("procedure.", data);
    }
}