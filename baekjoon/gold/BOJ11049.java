import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11049 {

    static int N;
    static int[] data;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        data = new int[N + 1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            data[i] = a;
            data[i + 1] = b;
        }

        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        System.out.println(solve(0, N - 1));
    }

    static int solve(int left, int right) {
        if (left == right) return 0;
        if (dp[left][right] != Integer.MAX_VALUE) return dp[left][right];

        for (int i = left; i < right; i++) {
            int value = solve(left, i) + solve(i + 1, right) + (data[left] * data[i + 1] * data[right + 1]);
            dp[left][right] = Math.min(dp[left][right], value);
        }

        return dp[left][right];
    }

}