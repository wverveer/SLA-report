package com.keylane;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.keylane.converter.ConvertFromSLARecord;
import com.keylane.fileWriter.FileWriter;
import com.keylane.model.SLARecord;
import com.keylane.reader.ConvertToSLARecord;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class SLAReportGeneratorTest {

    private FileWriter fileWriter;
    private ConvertToSLARecord reader;
    private ConvertFromSLARecord writer;
    private ConverterFactory factory;
    private SLAReportGenerator slaReportGenerator;

    @BeforeEach
    void setup() {
        fileWriter = mock(FileWriter.class);
        reader = mock(ConvertToSLARecord.class);
        writer = mock(ConvertFromSLARecord.class);
        factory = mock(ConverterFactory.class);
        slaReportGenerator = new SLAReportGenerator(fileWriter, factory);
    }

    @Test
    void generate() throws Exception {
        // Given.
        String inputPath = "inputPath.test";
        String outputPath = "outputPath.test";

        List<SLARecord> SLARecords = new ArrayList<>();
        SLARecords.add(new SLARecord(null, "test", 10));

        List<String> outputStrings = new ArrayList<>();
        outputStrings.add("test");

        when(reader.convert(inputPath)).thenReturn(SLARecords);
        when(writer.convert(SLARecords)).thenReturn(outputStrings);

        when(factory.createReader("test")).thenReturn(reader);
        when(factory.createWriter("test")).thenReturn(writer);

        // When.
        slaReportGenerator.generate(inputPath, outputPath);

        // Then.
        verify(fileWriter).write(outputStrings, outputPath);
    }

    @Test
    void inputExtensionUnsupported_NullPointer() {
        // Given.
        String inputPath = "inputPath.test";
        String outputPath = "outputPath.test";

        when(factory.createReader(anyString())).thenReturn(reader);
        when(factory.createWriter(anyString())).thenReturn(null);

        // When, then.
        assertThrows(NullPointerException.class, () -> slaReportGenerator.generate(inputPath, outputPath));
        verify(factory).createWriter("test");
        verify(factory).createReader("test");
        verifyNoMoreInteractions(fileWriter);
    }

    @Test
    void outputExtensionUnsupported_NullPointer() {
        // Given.
        String inputPath = "inputPath.test";
        String outputPath = "outputPath.test";

        when(factory.createReader(anyString())).thenReturn(null);
        when(factory.createWriter(anyString())).thenReturn(writer);

        // When, then.
        assertThrows(NullPointerException.class, () -> slaReportGenerator.generate(inputPath, outputPath));
        verify(factory).createWriter("test");
        verify(factory).createReader("test");
        verifyNoMoreInteractions(fileWriter);
    }

    @ParameterizedTest(name = "useStringAfterDotAsExtension | Input: {0} Expected: {1}")
    @CsvSource(
            {
                    "fileName.extension,extension",
                    "folder/fileName.extension,extension",
                    ".1.2,2"
            })
    void useStringAfterDotAsExtension(String input, String expected) throws Exception {
        when(factory.createReader(anyString())).thenReturn(reader);
        when(factory.createWriter(anyString())).thenReturn(writer);
        slaReportGenerator.generate(input, ".test");
        verify(factory).createReader(expected);
    }

    @ParameterizedTest(name = "noExtensionAfterFinalDot_NullPointerException | Input: {0} Expected: {1}")
    @CsvSource(
            {
                    "fileName",
                    ".",
                    ".."
            })
    void noExtensionAfterFinalDot_NullPointerException(String input) {
        assertThrows(NullPointerException.class, () -> slaReportGenerator.generate(input, ".test"));
        verifyNoMoreInteractions(factory);
    }
}