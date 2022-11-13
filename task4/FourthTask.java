import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FourthTask {

    public static void main(String[] args) throws IOException {
        FileReader input = new FileReader(args[0]);
        BufferedReader reader = new BufferedReader(input);

        List<Integer> nums = new ArrayList<>();

        while (reader.ready()) {
            nums.add(Integer.parseInt(reader.readLine()));
        }

        reader.close();
        input.close();

        System.out.println(calculate(nums));
    }

    public static int calculate(List<Integer> input) {
        Collections.sort(input);
        int middleIndex = input.size() / 2;
        int result = 0;

        for (Integer num : input) {
            result += Math.abs(num - input.get(middleIndex));
        }

        return result;
    }
}
