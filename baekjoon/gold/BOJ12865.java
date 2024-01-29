import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12865 {
    static int N, K;
    static Item[] items;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        items = new Item[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            items[i] = new Item(w, v);
        }

        findMaxValue();
    }

    private static void findMaxValue() {
        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            Item item = items[i];
            for (int j = 1; j <= K; j++) {
                if (item.w > j) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - item.w] + item.v);
            }
        }

        System.out.println(dp[N][K]);
    }

    static class Item {
        int w;
        int v;

        public Item(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }
}
