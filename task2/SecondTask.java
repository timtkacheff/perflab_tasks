import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SecondTask {

    public static void main(String[] args) throws IOException {
        FileReader circleParams = new FileReader(args[0]);
        FileReader pointsInput = new FileReader(args[1]);

        BufferedReader circleParamsReader = new BufferedReader(circleParams);
        String[] circleCenter = circleParamsReader.readLine().split(" ");
        String radius =  circleParamsReader.readLine();

        circleParamsReader.close();
        circleParams.close();

        List<String[]> points = new ArrayList<>();
        BufferedReader pointsReader = new BufferedReader(pointsInput);

        while(pointsReader.ready()) {
            points.add(pointsReader.readLine().split(" "));
        }

        pointsReader.close();
        pointsInput.close();

        for (String[] coordinates : points) {
            System.out.println(calculateResult(coordinates, circleCenter, radius));
        }
    }

    public static float[] parsePoints(String[] pointArray) {
        float x = Float.parseFloat(pointArray[0]);
        float y = Float.parseFloat(pointArray[1]);

        return new float[]{x, y};
    }

    private static int calculateResult(String[] pointCoordinates, String[] circleCenter, String radius) {
        float[] parsedPointCoordinates = parsePoints(pointCoordinates);
        float[] circleCenterCoordinates = parsePoints(circleCenter);
        float parsedRadius = Float.parseFloat(radius);

        float x = parsedPointCoordinates[0];
        float y = parsedPointCoordinates[1];

        float cx = circleCenterCoordinates[0];
        float cy = circleCenterCoordinates[1];

        float eq = (float) (Math.pow(x - cx, 2) + Math.pow(y - cy, 2));
        float eqRadius = (float) Math.pow(parsedRadius, 2);

        if (eq == eqRadius) {
            return 0;
        } else if (eq < eqRadius) {
            return 1;
        } else {
            return 2;
        }
    }
}
