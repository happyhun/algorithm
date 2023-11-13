import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2470 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] values = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(values);

        int start = 0;
        int end = values.length - 1;
        int prevSum = Integer.MAX_VALUE;
        int[] answer = new int[2];

        while (start < end) {
            int sum = values[start] + values[end];
            if (Math.abs(sum) < Math.abs(prevSum)) {
                prevSum = sum;
                answer[0] = values[start];
                answer[1] = values[end];
            }
            if (sum < 0) {
                start++;
            } else {
                end--;
            }
        }

        System.out.printf("%d %d\n", answer[0], answer[1]);
    }
}
