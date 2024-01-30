import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_15661_링크와스타트 {

    static int N;
    static int[][] S;
    static int[] team;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        // 변수 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N + 1][N + 1];
        team = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
    }

    private static void solve() {
        // 팀원 분배
        permutation(0);

        System.out.println(min);
    }

    private static void permutation(int idx) {
        if (idx == N) {
            // 능력치 계산
            int diff = calculateStatDiff();
            min = Math.min(min, diff);
            return;
        }

        for (int i = 0; i < 2; i++) {
            team[idx] = i;
            permutation(idx + 1);
        }
    }

    private static int calculateStatDiff() {
        List<Integer> start = new ArrayList<>();
        List<Integer> link = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (team[i] == 0) {
                start.add(i);
            } else {
                link.add(i);
            }
        }

        int sum1 = 0;
        for (int i = 0; i < start.size(); i++) {
            int me = start.get(i);
            for (int j = 0; j < start.size(); j++) {
                int other = start.get(j);
                sum1 += S[me][other];
            }
        }

        int sum2 = 0;
        for (int i = 0; i < link.size(); i++) {
            int me = link.get(i);
            for (int j = 0; j < link.size(); j++) {
                int other = link.get(j);
                sum2 += S[me][other];
            }
        }

        return Math.abs(sum1 - sum2);
    }
}
