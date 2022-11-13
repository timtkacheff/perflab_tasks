import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ThirdTask {

    public static void main(String[] args) throws IOException, ParseException {

        FileReader testsReader = new FileReader(args[0]);
        FileReader valuesReader = new FileReader(args[1]);

        JSONParser jsonParser = new JSONParser();
        JSONObject parsedTests = (JSONObject) jsonParser.parse(testsReader);
        JSONObject parsedValues = (JSONObject) jsonParser.parse(valuesReader);

        testsReader.close();
        valuesReader.close();

        JSONArray testsArray = (JSONArray) parsedTests.get("tests");
        JSONArray valuesArray = (JSONArray) parsedValues.get("values");

        System.out.println(writeReport(getResult(testsArray, valuesArray)));

    }

    public static JSONObject getResult(JSONArray tests, JSONArray values) {
        JSONObject res = new JSONObject();

        res.put("report", parseValues(tests, values));

        return res;
    }

    public static List<JSONObject> parseValues(JSONArray tests, JSONArray values) {

        List<JSONObject> resultingArray = new ArrayList<>();

        for (Object test : tests) {

            JSONObject result = new JSONObject();
            JSONObject testJSON = (JSONObject) test;

            if (testJSON.get("id") != null) {

                result.put("id", testJSON.get("id"));
                result.put("title", testJSON.get("title"));

                for (Object value : values) {

                    JSONObject valueJSON = (JSONObject) value;

                    if (testJSON.get("id").equals(valueJSON.get("id"))) {
                        result.put("value", valueJSON.get("value"));
                        break;
                    }
                }
                if (result.get("value") == null) {
                    result.put("value", "");
                }
            }
            if (testJSON.get("values") != null) {
                result.put("values", parseValues((JSONArray) testJSON.get("values"), values));
            }

            resultingArray.add(result);
        }
        return resultingArray;
    }

    public static String writeReport(JSONObject json) {

        File file = new File(System.getProperty("user.dir") + "\\report.json");

        System.out.println(file.getAbsolutePath());

        try (FileWriter writer = new FileWriter(file)) {
            json.writeJSONString(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Report has been written";
    }
}
