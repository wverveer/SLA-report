package com.keylane.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.keylane.model.SLARecord;

public class HTMLConverter implements ConvertFromSLARecord {

    private static class Helper {
        int totalDuration = 0;
        int totalCalls = 0;
        int ms0_300 = 0;
        int ms300_800 = 0;
        int ms800Plus = 0;
    }

    private Map<String, Helper> createHelperMap(List<SLARecord> records) {
        Map<String, Helper> map = new HashMap<>();

        for (SLARecord record : records) {
            String serviceName = record.getServiceName();
            int duration = record.getDurationInMs();

            if (!map.containsKey(serviceName)) {
                map.put(serviceName, new Helper());
            }
            map.get(serviceName).totalCalls += 1;
            map.get(serviceName).totalDuration += duration;
            if (duration >= 0 && duration < 300) {
                map.get(serviceName).ms0_300 += 1;
            } else if (duration >= 300 && duration < 800) {
                map.get(serviceName).ms300_800 += 1;
            } else if (duration >= 800) {
                map.get(serviceName).ms800Plus += 1;
            }
        }
        return map;
    }

    @Override
    public List<String> convert(List<SLARecord> records) {
        Map<String, Helper> map = createHelperMap(records);
        List<String> result = createFirstHTMLPart();

        for (String service : map.keySet()) {
            result.addAll(createHTMLServicePart(service, map.get(service)));
        }

        result.addAll(createHTMLFinalPart());

        return result;
    }

    private List<String> createFirstHTMLPart() {
        List<String> html = new ArrayList<>();
        html.add("<html lang=\"\">");
        html.add("<head>");
        html.add("    <script");
        html.add("            src=\"https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js\">");
        html.add("    </script>");
        html.add("    <title>Template</title>");
        html.add("</head>");
        html.add("<body>");

        return html;

    }

    private List<String> createHTMLServicePart(String serviceName, Helper helper) {
        String average = "NAN";
        if (helper.totalCalls > 0) {
            average = helper.totalDuration / helper.totalCalls + "ms";
        }

        List<String> html = new ArrayList<>();

        html.add("<h1>" + serviceName + "</h1>");
        html.add("Average: " + average + ".");
        html.add("<canvas id=\"" + serviceName + "\" style=\"width:100%;max-width:600px\"></canvas>");
        html.add("<script>");
        html.add("var xValues = [\"0-300ms\", \"300ms-800ms\", \"800ms +\"];");
        html.add("var yValues = [" + helper.ms0_300 + ", " + helper.ms300_800 + ", " + helper.ms800Plus + "];");
        html.add("var barColors = [\"red\", \"green\",\"blue\"];");
        html.add("new Chart(\"" + serviceName + "\", {");
        html.add("  type: \"pie\",");
        html.add("  data: {");
        html.add("    labels: xValues,");
        html.add("    datasets: [{");
        html.add("      backgroundColor: barColors,");
        html.add("      data: yValues");
        html.add("    }]");
        html.add("  }");
        html.add("});");
        html.add("</script>");

        return html;
    }

    private List<String> createHTMLFinalPart() {
        List<String> html = new ArrayList<>();

        html.add("</body>");
        html.add("</html>");

        return html;
    }


}
