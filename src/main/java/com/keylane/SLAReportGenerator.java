package com.keylane;

import java.util.Objects;

import com.keylane.converter.ConvertFromSLARecord;
import com.keylane.fileWriter.FileWriter;
import com.keylane.fileWriter.FileWriterImpl;
import com.keylane.reader.ConvertToSLARecord;

/**
 * Steps of the procedure:
 * 1. collect info for output
 * 2. read input (call input converter)
 * 3. create output (call output converter)
 */
public class SLAReportGenerator {

    private final FileWriter fileWriter;
    private final ConverterFactory converterFactory;

    public static void main(String[] args) throws Exception {
        SLAReportGenerator generator = new SLAReportGenerator(new FileWriterImpl(), new ConverterFactory());
        generator.generate(args[0], args[1]);
    }

    public SLAReportGenerator(FileWriter fileWriter, ConverterFactory converterFactory) {
        this.fileWriter = fileWriter;
        this.converterFactory = converterFactory;
    }

    public void generate(String input, String output) throws Exception {
        ConvertToSLARecord reader = converterFactory.createReader(Objects.requireNonNull(getFileExtension(input)));
        ConvertFromSLARecord converter = converterFactory.createWriter(Objects.requireNonNull(getFileExtension(output)));
        if (reader == null || converter == null) {
            throw new NullPointerException("Reader or Converter was null.");
        }
        fileWriter.write(converter.convert(reader.convert(input)), output);
    }

    private String getFileExtension(String path) {
        int startOfExtensionName = path.lastIndexOf(".");
        if (startOfExtensionName < 0 || path.length() - 1 == startOfExtensionName) {
            return null;
        }
        return path.substring(startOfExtensionName + 1);
    }
}


