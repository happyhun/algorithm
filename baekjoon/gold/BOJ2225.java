import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2225 {

    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        dp = new long[k][n+1];

        for (int i = 0; i < n + 1; i++) {
            dp[0][i] = 1;
        }

        for (int i = 0; i < k; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < k; i++) {
            for (int j = 1; j < n + 1; j++) {
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000000;
            }
        }

        System.out.println(dp[k-1][n]);
    }

}
