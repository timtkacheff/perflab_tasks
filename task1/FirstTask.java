public class FirstTask {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        int[] array = createArray(n);
        int i = 0;

        System.out.print(array[i]);

        while (true) {
            int step = i + m - 1;

            if (step < n) {
                i = step;
            } else {
                i = Math.abs(n - step);
                while (i >= n) {
                    i = Math.abs(n - i);
                }
            }

            if (array[0] == array[i]) {
                break;
            }

            System.out.print(array[i]);

        }
    }

    public static int[] createArray(int length) {
        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = i + 1;
        }

        return array;
    }
}
