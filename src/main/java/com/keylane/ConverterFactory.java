package com.keylane;

import java.util.Map;
import java.util.function.Supplier;

import com.keylane.converter.CSVConverter;
import com.keylane.converter.ConvertFromSLARecord;
import com.keylane.converter.HTMLConverter;
import com.keylane.reader.CSVReader;
import com.keylane.reader.ConvertToSLARecord;
import com.keylane.reader.JSONReader;
import com.keylane.reader.XMLReader;

public class ConverterFactory {

    private final Map<String, Supplier<ConvertToSLARecord>> readerMap = Map.of(
            "xml", XMLReader::new,
            "json", JSONReader::new,
            "csv", CSVReader::new
    );
    private final Map<String, Supplier<ConvertFromSLARecord>> converterMap = Map.of(
            "csv", CSVConverter::new,
            "html", HTMLConverter::new
    );

    public Map<String, Supplier<ConvertToSLARecord>> getReaderMap() {
        return readerMap;
    }

    public Map<String, Supplier<ConvertFromSLARecord>> getConverterMap() {
        return converterMap;
    }

    public ConvertFromSLARecord createWriter(String extension) {
        return converterMap.get(extension.toLowerCase()) == null ? null : converterMap.get(extension.toLowerCase()).get();
    }

    public ConvertToSLARecord createReader(String extension) {
        return readerMap.get(extension.toLowerCase()) == null ? null : readerMap.get(extension.toLowerCase()).get();
    }
}
