import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2133 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N % 2 == 1) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[31];

        dp[2] = 3;
        dp[4] = 11;

        int sum = 0;

        for (int i = 6; i <= N; i += 2) {
            sum += dp[i - 4] * 2;
            dp[i] = dp[i - 2] * 3 + sum + 2;
        }

        System.out.println(dp[N]);
    }
}