import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1027 {

    static int N;
    static int[] buildings;
    static int[] counts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        buildings = new int[N];
        counts = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N - 1; i++) {
            counts[i]++;
            counts[i + 1]++;
            double slope = buildings[i + 1] - buildings[i];
            for (int j = i + 2; j < N; j++) {
                double nextSlope = calculate(i, j);
                if (nextSlope > slope) {
                    slope = nextSlope;
                    counts[i]++;
                    counts[j]++;
                }
            }
        }

        int answer = counts[0];
        for (int i = 1; i < counts.length; i++) {
            answer = Math.max(answer, counts[i]);
        }

        System.out.println(answer);
    }

    private static double calculate(int i, int j) {
        return (double) (buildings[j] - buildings[i]) / (j - i);
    }
}