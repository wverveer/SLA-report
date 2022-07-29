package com.keylane.reader;

import com.keylane.model.SLARecord;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLReader implements ConvertToSLARecord {
    public List<SLARecord> convert(String inputFilePath) throws Exception{
        // Instantiate the Factory.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        List<SLARecord> input = new ArrayList<SLARecord>();

        // Process XML securely, avoid attacks like XML External Entities (XXE).
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        // Parse XML file.
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.parse(new File(inputFilePath));
        doc.getDocumentElement().normalize();

        // Get events.
        NodeList list = doc.getElementsByTagName("event");

        for (int i = 0; i < list.getLength(); i++) {
            // Convert timestamp to Date format.
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date timestamp = format.parse(list.item(i).getChildNodes().item(5).getFirstChild().getNodeValue());

            // Insert data to SLARecord list.
            input.add(new SLARecord(timestamp,
                    list.item(i).getChildNodes().item(3).getFirstChild().getNodeValue(),
                    Integer.parseInt(list.item(i).getChildNodes().item(1).getFirstChild().getNodeValue())));
        }

        return input;
    }
}
