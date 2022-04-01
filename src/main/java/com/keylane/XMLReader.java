package com.keylane;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLReader implements ConvertToSLARecord {
    public List<SLARecord> convert(String inputFilePath){
        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        List<SLARecord> input = new ArrayList<SLARecord>();

        try {
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(inputFilePath));
            doc.getDocumentElement().normalize();

            // get <staff>
            NodeList list = doc.getElementsByTagName("event");

            for (int i = 0; i < list.getLength(); i++) {
                //Convert timestamp to Date format
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date timestamp = format.parse(list.item(i).getChildNodes().item(5).getFirstChild().getNodeValue());

                //Insert data to SLARecord list
                input.add(new SLARecord(timestamp,
                        list.item(i).getChildNodes().item(3).getFirstChild().getNodeValue(),
                        Integer.parseInt(list.item(i).getChildNodes().item(1).getFirstChild().getNodeValue())));
            }
        } catch (ParserConfigurationException | SAXException | IOException | ParseException e) {
            e.printStackTrace();
        }
        return input;
    }
}
