import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int T, N, M;
	static int[] coins;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());
			coins = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				coins[j] = Integer.parseInt(st.nextToken());
			}
			M = Integer.parseInt(br.readLine());

			int answer = solve();
			System.out.println(answer);
		}
	}

	private static int solve() {
		int[][] dp = new int[N + 1][M + 1];
		for (int i = coins[1]; i <= M; i += coins[1]) {
			dp[1][i] = 1;
		}

		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				// 동전 액수전까지는 위쪽값 그대로 사용
				if (j < coins[i]) {
					dp[i][j] = dp[i - 1][j];
					continue;
				}

				if (j == coins[i]) {
					dp[i][j] = dp[i - 1][j] + 1;
					continue;
				}

				dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
			}
		}

		return dp[N][M];
	}
}
