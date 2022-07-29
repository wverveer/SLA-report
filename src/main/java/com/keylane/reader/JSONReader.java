package com.keylane.reader;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.keylane.model.SLARecord;

public class JSONReader implements ConvertToSLARecord {

    @Override
    public List<SLARecord> convert(String inputFilePath) throws Exception {
        List<SLARecord> SLARecordsList = new ArrayList<>();

        List<JSONObject> JSONObjectList = createJSONObjectListFromInput(inputFilePath);
        for (JSONObject object : JSONObjectList) {
            SLARecordsList.add(createSLARecord(object));
        }
        return SLARecordsList;
    }

    private List<JSONObject> createJSONObjectListFromInput(String inputFilePath) throws Exception {
        List<JSONObject> JSONObjectList = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(inputFilePath)) {
            JSONArray events = (JSONArray) ((JSONObject) jsonParser.parse(reader)).get("events");
            for (Object object : events) {
                JSONObjectList.add((JSONObject) object);
            }
        }
        return JSONObjectList;
    }

    private SLARecord createSLARecord(JSONObject jsonObject) throws Exception {
        Date timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jsonObject.get("timestamp").toString());
        String serviceName = jsonObject.get("service").toString();
        int durationInMs = Integer.parseInt(jsonObject.get("duration_in_ms").toString());
        return new SLARecord(timestamp, serviceName, durationInMs);
    }
}
