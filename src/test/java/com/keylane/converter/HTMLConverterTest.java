package com.keylane.converter;

import com.keylane.model.SLARecord;
import com.keylane.converter.HTMLConverter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HTMLConverterTest {

    @Test
    void convert() {
        // Given.
        List<SLARecord> list = new ArrayList<>();
        list.add(new SLARecord(null, "TestService1", 100));
        list.add(new SLARecord(null, "TestService1", 300));
        list.add(new SLARecord(null, "TestService1", 300));
        list.add(new SLARecord(null, "TestService1", 100));
        list.add(new SLARecord(null, "TestService1", 400));
        list.add(new SLARecord(null, "TestService1", 600));
        list.add(new SLARecord(null, "TestService1", 1070));

        list.add(new SLARecord(null, "TestService2", 100));

        // When.
        HTMLConverter convert = new HTMLConverter();
        List<String> result = convert.convert(list);

        // Then.
        List<String> expected = createTestHTML();
        assertEquals(expected.size(), result.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), result.get(i));
        }
    }

    private List<String> createTestHTML() {
        List<String> html = new ArrayList<>();
        html.add("<html lang=\"\">");
        html.add("<head>");
        html.add("    <script");
        html.add("            src=\"https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js\">");
        html.add("    </script>");
        html.add("    <title>Template</title>");
        html.add("</head>");
        html.add("<body>");

        html.add("<h1>TestService1</h1>");
        html.add("Average: 410ms.");
        html.add("<canvas id=\"TestService1\" style=\"width:100%;max-width:600px\"></canvas>");
        html.add("<script>");
        html.add("var xValues = [\"0-300ms\", \"300ms-800ms\", \"800ms +\"];");
        html.add("var yValues = [2, 4, 1];");
        html.add("var barColors = [\"red\", \"green\",\"blue\"];");
        html.add("new Chart(\"TestService1\", {");
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

        html.add("<h1>TestService2</h1>");
        html.add("Average: 100ms.");
        html.add("<canvas id=\"TestService2\" style=\"width:100%;max-width:600px\"></canvas>");
        html.add("<script>");
        html.add("var xValues = [\"0-300ms\", \"300ms-800ms\", \"800ms +\"];");
        html.add("var yValues = [1, 0, 0];");
        html.add("var barColors = [\"red\", \"green\",\"blue\"];");
        html.add("new Chart(\"TestService2\", {");
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

        html.add("</body>");
        html.add("</html>");

        return html;
    }
}